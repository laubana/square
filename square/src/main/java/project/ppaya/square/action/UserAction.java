package project.ppaya.square.action;

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

import project.ppaya.square.shdao.*;
import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class UserAction {

	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleAttendanceDAO yh_event_schedule_attendanceDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHEventScheduleImageFaceDAO yh_event_schedule_image_faceDAO;
	@Autowired
	YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHUserHashtagDAO yhuser_hashtagDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO; 
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@ResponseBody
	@RequestMapping(value = "verifyUserIdAction", method = RequestMethod.POST)
	public String verifyUserIdAction(String user_id)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		
		if(user != null)
		{
			return "false";
		}
		else
		{
			return "true";
		}
	}
	@ResponseBody
	@RequestMapping(value = "loginUserFormAction", method = RequestMethod.POST)
	public String loginUserFormAction(HttpSession session, String user_id, String password)
	{
		User user = yh_userDAO.selectUserByUserIdPassword(user_id, password);
		
		if(user != null)
		{
			session.setAttribute("user_id", user_id);
			return "true";
		}
		else
		{
			return "false";
		}
	}
	@ResponseBody
	@RequestMapping(value = "logoutUserAction", method = RequestMethod.POST)
	public void logoutUserAction(HttpSession session)
	{
		session.removeAttribute("user_id");
	}
	@ResponseBody
	@RequestMapping(value = "/testAction2", method = RequestMethod.POST)
	public void testAction2(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		ArrayList<String> checked_event_schedule_image_id_list = (ArrayList<String>)map.get("checked_event_schedule_image_id_list");
		ArrayList<String> unchecked_event_schedule_image_id_list = (ArrayList<String>)map.get("unchecked_event_schedule_image_id_list");
		ArrayList<String> checked_event_schedule_video_id_list = (ArrayList<String>)map.get("checked_event_schedule_video_id_list");
		ArrayList<String> unchecked_event_schedule_video_id_list = (ArrayList<String>)map.get("unchecked_event_schedule_video_id_list");
		
		for(int i = 0; i < checked_event_schedule_image_id_list.size(); i++)
		{
			yh_image_albumDAO.updateBlindByUserIdEventScheduleImageId(user_id, checked_event_schedule_image_id_list.get(i), 0);
		}
		for(int i = 0; i < unchecked_event_schedule_image_id_list.size(); i++)
		{
			yh_image_albumDAO.updateBlindByUserIdEventScheduleImageId(user_id, unchecked_event_schedule_image_id_list.get(i), 1);
		}
		for(int i = 0; i < checked_event_schedule_video_id_list.size(); i++)
		{
			yh_video_albumDAO.updateBlindByUserIdEventScheduleVideoId(user_id, checked_event_schedule_video_id_list.get(i), 0);
		}
		for(int i = 0; i < unchecked_event_schedule_video_id_list.size(); i++)
		{
			yh_video_albumDAO.updateBlindByUserIdEventScheduleVideoId(user_id, unchecked_event_schedule_video_id_list.get(i), 1);
		}
	}
	@ResponseBody
	@RequestMapping(value = "/testAction", method = RequestMethod.POST)
	public HashMap<String, Object> testAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		ArrayList<Integer> group_id_list = (ArrayList<Integer>)map.get("group_id_list");
		boolean self = (boolean)map.get("self");
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> old_event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<Integer> attendance_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<Integer> new_event_schedule_id_list = new ArrayList<>();
		for(int i = 0; i < old_event_schedule_id_list.size(); i++)
		{
			for(int j = 0; j < attendance_event_schedule_id_list.size(); j++)
			{
				if(old_event_schedule_id_list.get(i) == attendance_event_schedule_id_list.get(j))
				{
					new_event_schedule_id_list.add(old_event_schedule_id_list.get(i));
					break;
				}
			}
		}
		
		ArrayList<String> event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(new_event_schedule_id_list);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(new_event_schedule_id_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list;
		
		ArrayList<EventScheduleVideo> event_schedule_video_list;
		
		if(self == true)
		{
			event_schedule_image_id_list = yh_image_albumDAO.getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id);
			event_schedule_video_id_list = yh_video_albumDAO.getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(event_schedule_video_id_list, user_id);
			
			event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleImageIdList(event_schedule_image_id_list);
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				event_schedule_image_list.get(i).setBlind(yh_image_albumDAO.getBlindByUserIdEventScheduleImageId(user_id, event_schedule_image_list.get(i).getEvent_schedule_image_id()));
			}
			event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
			for(int i = 0; i < event_schedule_video_list.size(); i++)
			{
				event_schedule_video_list.get(i).setBlind(yh_video_albumDAO.getBlindByUserIdEventScheduleVideoId(user_id, event_schedule_video_list.get(i).getEvent_schedule_video_id()));
			}
		}
		else
		{
			event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(new_event_schedule_id_list);
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				event_schedule_image_list.get(i).setBlind(yh_image_albumDAO.getBlindByUserIdEventScheduleImageId(user_id, event_schedule_image_list.get(i).getEvent_schedule_image_id()));
			}
			event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(new_event_schedule_id_list);
		}

		HashMap<String, Object> result = new HashMap<>();
		result.put("event_schedule_image_list", event_schedule_image_list);
		result.put("event_schedule_video_list", event_schedule_video_list);
		
		return result;
	}
}
