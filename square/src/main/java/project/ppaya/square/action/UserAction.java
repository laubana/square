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
import project.ppaya.square.yhthread.YHRefreshAlbumThread;
import project.ppaya.square.yhthread.YHUpdateVideoAlbumThread;
import project.ppaya.square.yhutil.*;

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
	YHVideoAppearanceDAO yh_video_appearanceDAO;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@ResponseBody
	@RequestMapping(value = "updateUserImageAction", method = RequestMethod.POST)
	public String updateUserImageAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		String image_id = YHFileUtil.saveJpegFromBase64((String)map.get("image"), Reference.user_image_path);
		
		yh_userDAO.updateUserImage(user_id, image_id);
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "updateContentAction", method = RequestMethod.POST)
	public String updateContentAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		String content = (String)map.get("content");
		
		yh_userDAO.updateContentByUserId(user_id, content);
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "verifyUserIdAction", method = RequestMethod.POST)
	public String verifyUserIdAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		
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
	@RequestMapping(value = "loginUserAction", method = RequestMethod.POST)
	public String loginUserAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		String password = (String)map.get("password");
		
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
	@RequestMapping(value = "joinUserAction", method = RequestMethod.POST)
	public String joinUserAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		String password = (String)map.get("password");
		String name = (String)map.get("name");
		String region = (String)map.get("region");
		
		int result = yh_userDAO.insertUser(user_id, password, name, region);
		
		if(result != 0)
		{
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
	@RequestMapping(value = "/setGroupAlbumAction", method = RequestMethod.POST)
	public void setGroupAlbumAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		ArrayList<String> checked_group_id_list = (ArrayList<String>)map.get("checked_group_id_list");
		ArrayList<String> unchecked_group_id_list = (ArrayList<String>)map.get("unchecked_group_id_list");
		
		for(int i = 0; i < checked_group_id_list.size(); i++)
		{
			yh_group_attendanceDAO.updateBlindByUserIdGroupId(user_id, Integer.parseInt(checked_group_id_list.get(i)), 0);
		}
		for(int i = 0; i < unchecked_group_id_list.size(); i++)
		{
			yh_group_attendanceDAO.updateBlindByUserIdGroupId(user_id, Integer.parseInt(unchecked_group_id_list.get(i)), 1);
		}
	}
	@ResponseBody
	@RequestMapping(value = "/setAlbumAction", method = RequestMethod.POST)
	public void setAlbumAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
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
	@RequestMapping(value = "/refreshAlbumAction", method = RequestMethod.POST)
	public HashMap<String, Object> refreshAlbumAction(HttpSession session, Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		ArrayList<Integer> group_id_list = (ArrayList<Integer>)map.get("group_id_list");
		boolean self = (boolean)map.get("self");
		
		User user = yh_userDAO.selectUserByUserId(user_id);
		
		ArrayList<Integer> attendance_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByGroupIdListEventScheduleIdList(group_id_list, attendance_event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list;
		
		ArrayList<EventScheduleVideo> event_schedule_video_list;		

		ArrayList<HashMap<String, Object>> image_list = new ArrayList<>();
		ArrayList<HashMap<String, Object>> video_list = new ArrayList<>();
		
		if(self == true)
		{
			event_schedule_image_id_list = yh_image_albumDAO.getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id);
			event_schedule_video_id_list = yh_video_albumDAO.getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(event_schedule_video_id_list, user_id);
			
			event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleImageIdList(event_schedule_image_id_list);
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				HashMap<String, Object> image_list_map = new HashMap<>();
				
				image_list_map.put("index", i);
				image_list_map.put("image", event_schedule_image_list.get(i));
				image_list_map.put("blind", yh_image_albumDAO.getBlindByUserIdEventScheduleImageId(user_id, event_schedule_image_list.get(i).getEvent_schedule_image_id()));
				
				image_list.add(image_list_map);
			}
			
			event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
			int index = 0;
			YHRefreshAlbumThread.out_map.put(index, new HashMap<>());
			YHRefreshAlbumThread.video_list = new ArrayList<>();
			for(int i = 0; i < event_schedule_video_list.size(); i++)
			{
				YHRefreshAlbumThread thread = new YHRefreshAlbumThread(index, i, event_schedule_video_list.get(i), user);
				thread.start();
				/*HashMap<String, Object> video_list_map = new HashMap<>();
				
				video_list_map.put("index", i);
				video_list_map.put("video", event_schedule_video_list.get(i));
				video_list_map.put("blind", yh_video_albumDAO.getBlindByUserIdEventScheduleVideoId(user_id, event_schedule_video_list.get(i).getEvent_schedule_video_id()));
				
				ArrayList<String> face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				if(face_id_list.size() != 0)
				{
					ArrayList<String> similar_face_id_list = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(face_id_list, YHMSFaceUtil.getFaceId(Reference.user_image_path, user.getImage_id()));
					video_list_map.put("appearance_list", yh_video_appearanceDAO.selectVideoAppearanceByFaceIdList(similar_face_id_list));
				}
				else
				{
					video_list_map.put("appearance_list", new ArrayList<VideoAppearance>());
				}
				
				video_list.add(video_list_map);*/
			}
			out_while:
			while(true)
			{
				try
				{
					Thread.sleep(100);
				}
				catch(Exception error){error.printStackTrace();}
				for(int i = 0; i < event_schedule_video_list.size(); i++)
				{
					if((boolean)YHRefreshAlbumThread.out_map.get(index).get(i) == false)
					{
						continue out_while;
					}
				}
				break;
			}
			video_list = YHRefreshAlbumThread.video_list;
		}
		else
		{
			event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				HashMap<String, Object> image_list_map = new HashMap<>();
				
				image_list_map.put("index", i);
				image_list_map.put("image", event_schedule_image_list.get(i));
				image_list_map.put("blind", yh_image_albumDAO.getBlindByUserIdEventScheduleImageId(user_id, event_schedule_image_list.get(i).getEvent_schedule_image_id()));
				
				image_list.add(image_list_map);
			}
			
			event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
			int index = 0;
			YHRefreshAlbumThread.out_map.put(index, new HashMap<>());
			YHRefreshAlbumThread.video_list = new ArrayList<>();
			for(int i = 0; i < event_schedule_video_list.size(); i++)
			{
				YHRefreshAlbumThread thread = new YHRefreshAlbumThread(index, i, event_schedule_video_list.get(i), user);
				thread.start();
				/*HashMap<String, Object> video_list_map = new HashMap<>();
				
				video_list_map.put("index", i);
				video_list_map.put("video", event_schedule_video_list.get(i));
				video_list_map.put("blind", yh_video_albumDAO.getBlindByUserIdEventScheduleVideoId(user_id, event_schedule_video_list.get(i).getEvent_schedule_video_id()));
				
				ArrayList<String> face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				if(face_id_list.size() != 0)
				{
					ArrayList<String> similar_face_id_list = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(face_id_list, YHMSFaceUtil.getFaceId(Reference.user_image_path, user.getImage_id()));
					video_list_map.put("appearance_list", yh_video_appearanceDAO.selectVideoAppearanceByFaceIdList(similar_face_id_list));
				}
				else
				{
					video_list_map.put("appearance_list", new ArrayList<VideoAppearance>());
				}
				
				video_list.add(video_list_map);*/
			}
			out_while:
			while(true)
			{
				try
				{
					Thread.sleep(100);
				}
				catch(Exception error){error.printStackTrace();}
				for(int i = 0; i < event_schedule_video_list.size(); i++)
				{
					if((boolean)YHRefreshAlbumThread.out_map.get(index).get(i) == false)
					{
						continue out_while;
					}
				}
				break;
			}
			video_list = YHRefreshAlbumThread.video_list;
		}

		HashMap<String, Object> result = new HashMap<>();
		result.put("image_list", image_list);
		result.put("video_list", video_list);
		
		return result;
	}
}
