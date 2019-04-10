package project.ppaya.square.yhaction;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.vo.ImageAlbum;
import project.ppaya.square.yhdao.YHImageAlbumDAO;
import project.ppaya.square.yhdao.YHVideoAlbumDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoDAO;
import project.ppaya.square.yhutil.YHUserFormUtil;

@Repository
@Controller
public class YHUserAction
{	
	private static final Logger logger = LoggerFactory.getLogger(YHUserAction.class);

	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	
	@ResponseBody
	@RequestMapping(value = "/getEventScheduleImageListPOST", method = RequestMethod.POST)
	public void getEventScheduleImageListPOST(HttpSession session, @RequestBody ArrayList<Integer> group_id_list)
	{
		String user_id = "id1";
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		yh_image_albumDAO.selectImageAlbumByEventScheduleImageIdListUserId(event_schedule_image_id_list, user_id);
		yh_image_albumDAO.selectImageAlbumByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id);
	}
	@ResponseBody
	@RequestMapping(value = "/testAction", method = RequestMethod.POST)
	public HashMap<String, Object> testAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = "id1@gmail.com";
		ArrayList<Integer> group_id_list = (ArrayList<Integer>)map.get("group_id_list");
		boolean self = (boolean)map.get("self");
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list;
		
		ArrayList<EventScheduleVideo> event_schedule_video_list;
		
		if(self == true)
		{
			event_schedule_image_id_list = yh_image_albumDAO.getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id);
			event_schedule_video_id_list = yh_video_albumDAO.getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(event_schedule_video_id_list, user_id);
			
			event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleImageIdList(event_schedule_image_id_list);
			event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
		}
		else
		{
			event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
			event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		}

		HashMap<String, Object> result = new HashMap<>();
		result.put("event_schedule_image_list", event_schedule_image_list);
		result.put("event_schedule_video_list", event_schedule_video_list);
		
		return result;
	}
}
