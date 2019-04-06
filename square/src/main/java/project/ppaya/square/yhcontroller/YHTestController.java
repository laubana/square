package project.ppaya.square.yhcontroller;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.vo.EventScheduleVideoFace;
import project.ppaya.square.vo.Reference;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoFaceDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhutil.YHFileUtil;
import project.ppaya.square.yhutil.YHMSFaceUtil;
import project.ppaya.square.yhutil.YHVideoIndexerUtil;

/**
 * Handles requests for the application home page.
 */
@Repository
@Controller
public class YHTestController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestController.class);
	
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduledDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	
	@RequestMapping(value = "yhtest", method = RequestMethod.GET)
	public void test(int group_id)
	{
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(group_id);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduledDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			String event_schedule_video_id = event_schedule_video_id_list.get(i);
			
			EventScheduleVideo event_schedule_video = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoId(event_schedule_video_id);
			
			if(event_schedule_video.getDetect_date() == null)
			{
				JSONObject jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					JSONArray jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(i).getString("thumbnailId")), Reference.file_path + "\\test");
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.file_path + "\\test", event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
					
					ArrayList<String> event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id);
				}
				else
				{
					logger.debug("Error");
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video.getDetect_date())
			{
				long detect_date = (new Date()).getTime();
				
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId(detect_date, event_schedule_video_id);
				
				ArrayList<EventScheduleVideoFace> event_schedule_video_face_list = yh_event_schedule_video_faceDAO.selectEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_id);
				
				for(int j = 0; j < event_schedule_video_face_list.size(); j++)
				{
					yh_event_schedule_video_faceDAO.updateEventScheduleVideoFaceIdByEventScheduleVideoImageId(YHMSFaceUtil.detectFace(Reference.file_path + "\\", event_schedule_video_face_list.get(i).getEvent_schedule_video_image_id()), event_schedule_video_face_list.get(i).getEvent_schedule_video_image_id());
				}
				
				ArrayList<String> event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id);
				
				
			}
			else
			{
				ArrayList<String> event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id);
				
				
			}
		}
	}
}
