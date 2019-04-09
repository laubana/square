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
import project.ppaya.square.vo.User;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleAttendanceDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoFaceDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHUserDAO;
import project.ppaya.square.yhdao.YHVideoAlbumDAO;
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
	YHUserDAO yh_userDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduledDAO;
	@Autowired
	YHEventScheduleAttendanceDAO yh_event_schedule_attendanceDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	
	@RequestMapping(value = "/yhtest1", method = RequestMethod.GET)
	public void yhtest1()
	{
		try
		{
			CalendarQuickstart.main();
		}
		catch(Exception error){error.printStackTrace();}
	}
	@RequestMapping(value = "yhtest2", method = RequestMethod.GET)
	public void yhtest2()
	{
		String user_id = "id1";
		User user = yh_userDAO.selectUserByUserId(user_id);
		String image_id = user.getImage_id();
		
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			String event_schedule_video_id = event_schedule_video_id_list.get(i);
			
			EventScheduleVideo event_schedule_video = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoId(event_schedule_video_id);
			
			if(event_schedule_video.getDetect_date() == null)
			{
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_id);
				
				JSONObject jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					JSONArray jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_image_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_image_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video.getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_id);
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_id);
				
				JSONObject jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					JSONArray jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_image_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_image_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
				}		
			}
		}
		
		ArrayList<Integer> attend_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(attend_event_schedule_id_list);
		
		yh_video_albumDAO.deleteVideoAlbumByNotEventScheduleVideoIdUserId(attend_event_schedule_video_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_video_id_list.size(); i++)
		{
			yh_video_albumDAO.insertVideoAlbum(attend_event_schedule_video_id_list.get(i), user_id);
		}
		
		yh_video_albumDAO.updateSelfByUserId(user_id);
		
		for(int i = 0; i < attend_event_schedule_video_id_list.size(); i++)
		{
			ArrayList<String> attend_event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(attend_event_schedule_video_id_list.get(i));
			
			ArrayList<String> similar_event_schedule_video_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_video_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_image_path, image_id))).getJSONObject(0).getString("faceId"));
			
			if(similar_event_schedule_video_face_id.size() != 0)
			{
				yh_video_albumDAO.updateSelfByEventScheduleVideoIdUserId(attend_event_schedule_video_id_list.get(i), user_id);
			}
		}
	}
}
