package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.ppaya.square.shdao.*;
import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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
	YHUtil yh_util;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@RequestMapping(value = "listUserGroupForm", method = RequestMethod.GET)
	public String listUserGroupForm
	(
			Model request,
			@RequestParam(value = "user_id", defaultValue = "id1@gmail.com") String user_id
			)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		//User 전송
		request.addAttribute("user", user);
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserId(user_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);
		for(int i = 0; i < group_list.size(); i++)
		{
			group_list.get(i).setBlind(yh_group_attendanceDAO.getBlindByUserIdGroupId(user_id, group_list.get(i).getGroup_id()));
		}
		
		//Group List 전송
		request.addAttribute("group_list", group_list);
		
		return "user/listUserGroupForm";
	}
	@RequestMapping(value = "joinUserForm", method = RequestMethod.GET)
	public String joinUserFormGET()
	{
		return "user/joinUserForm";
	}
	@RequestMapping(value = "loginUserForm", method = RequestMethod.GET)
	public String loginUserFormGET()
	{
		return "user/loginUserForm";
	}
	@RequestMapping(value = "viewUserForm", method = RequestMethod.GET)
	public String viewUserForm
	(
			Model request,
			@RequestParam(value = "user_id", defaultValue = "id1@gmail.com") String user_id
			//String user_id
			)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		//User 전송
		request.addAttribute("user", user);
		
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserIdNotBlind(user_id);
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);
		//Group List 전송
		request.addAttribute("group_list", group_list);
		
		ArrayList<UserHashtag> user_hashtag_list = yhuser_hashtagDAO.selectUserHashtagByUserId(user_id);
		//UserHashtag List 전송
		request.addAttribute("user_hashtag_list", user_hashtag_list);
		
		ArrayList<String> event_schedule_image_id_list = yh_image_albumDAO.getEventScheduleImageIdByUserIdNotBlind(user_id);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleImageIdList(event_schedule_image_id_list);
		//EventScheduleImage List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<String> event_schedule_video_id_list = yh_video_albumDAO.getEventScheduleVideoIdByUserIdNotBlind(user_id);
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
		//EventScheduleVideo List 전송
		
		ArrayList<HashMap<String, Object>> video_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_video_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("video", event_schedule_video_list.get(i));
			
			ArrayList<String> face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
			
			ArrayList<User> user_list = yh_userDAO.selectUser();
			
			ArrayList<ArrayList<VideoAppearance>> video_appearance_list_list = new ArrayList<>();
			
			for(int j = 0; j < user_list.size(); j++)
			{
				ArrayList<String> similar_face_id_list = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(face_id_list, (new JSONArray(YHMSFaceUtil.getFace(Reference.user_image_path, user_list.get(j).getImage_id()))).getJSONObject(0).getString("faceId"));
				
				ArrayList<VideoAppearance> video_appearance_list = yh_video_appearanceDAO.selectVideoAppearanceByFaceIdList(similar_face_id_list);
				
				video_appearance_list_list.add(video_appearance_list);
			}
			map.put("user_list", user_list);
			map.put("video_appearance_list_list", video_appearance_list_list);
			
			video_list.add(map);
		}
		request.addAttribute("json_temp_video_list", new JSONArray(video_list));
		request.addAttribute("temp_video_list", video_list);
		request.addAttribute("video_list", event_schedule_video_list);
		
		return "user/viewUserForm";
	}
	@RequestMapping(value = "listUserAlbumForm", method = RequestMethod.GET)
	public String listUserAlbumForm
	(
			Model request,
			@RequestParam(value = "user_id", defaultValue = "id1@gmail.com") String user_id
			//String user_id
			)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;	
		
		User user = yh_userDAO.selectUserByUserId(user_id);
		//User 전송
		request.addAttribute("user", user);
		String image_id = user.getImage_id();
		
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserId(user_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);		
		request.addAttribute("group_list", group_list);
		
		yh_util.updateEventScheduleImageFace(user_id);
		yh_util.updateImageAlbum(user_id);
		yh_util.updateEventScheduleVideoFace(user_id);
		yh_util.updateVideoAlbum(user_id);
		
		return "user/listUserAlbumForm";
	}
}
