package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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

@Controller
public class GroupController
{	
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHGroupCategoryDAO yh_group_categoryDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageDescriptionDAO yh_event_schedule_image_descriptionDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHGroupCommentDAO yh_group_commentDAO;
	@Autowired
	YHGroupCommentTagDAO yh_group_comment_tagDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	@Autowired
	YHEventUnionDAO yh_event_unionDAO;
	@Autowired
	YHKeywordHistoryDAO yh_keyword_historyDAO;
	
	@Autowired
	SH_DAO_Group sh_gdao;
	@Autowired
	SH_DAO_User sh_udao;
	
	@RequestMapping(value = "createGroupForm", method = RequestMethod.GET)
	public String createGroupForm(Model request)
	{
		return "group/createGroupForm";
	}
	@RequestMapping(value = "listGroupForm", method = RequestMethod.GET)
	public String listGroupForm
	(
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			//int group_category_id,
			Model request
			)
	{
		ArrayList<String> hashtag_list = yh_keyword_historyDAO.getKeywordByRank();
		//Keyword List 전송
		request.addAttribute("hashtag_list", hashtag_list);
		
		GroupCategory group_category = yh_group_categoryDAO.selectGroupCategoryByGroupCategoryId(group_category_id);
		//GroupCategory 전송
		request.addAttribute("group_category", group_category);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupCategoryId(group_category_id);
		//Group List 전송
		request.addAttribute("group_list", group_list);

		return "group/listGroupForm";
	}
	@RequestMapping(value = "viewGroupForm", method = RequestMethod.GET)
	public String viewGroupForm
	(
			HttpSession session,
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_category_id
			//int group_id
			)
	{
		String user_id = (String)session.getAttribute("user_id"); 
		if(user_id != null)
		{
			GroupAttendance group_attendance = yh_group_attendanceDAO.selectGroupAttendanceByGroupIdUserId(user_id, group_id);
			request.addAttribute("group_attendance", group_attendance);
		}
		else
		{
			request.addAttribute("group_attendance", null);
		}
		
		GroupCategory group_category = yh_group_categoryDAO.selectGroupCategoryByGroupCategoryId(group_category_id);
		//GroupCategory 전송
		request.addAttribute("group_category", group_category);
		
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		User leader = yh_userDAO.selectUserByUserId(group.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
		
		ArrayList<String> user_id_list = yh_group_attendanceDAO.getUserIdByGroupId(group_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(user_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);
		
		ArrayList<Event> event_list = yh_eventDAO.selectEventByGroupId(group_id);
		//Event List 전송
		request.addAttribute("event_list", event_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByGroupId(group_id);
		ArrayList<HashMap<String, Object>> image_list = new ArrayList<>();		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("image", event_schedule_image_list.get(i));
			
			image_list.add(map);
		}
		//EventScheduleImage List 전송
		request.addAttribute("image_list", image_list);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByGroupId(group_id);
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		//Video List 전송
		request.addAttribute("video_list", event_schedule_video_list);
		
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(group_id);		
		ArrayList<HashMap<String, Object>> comment_list = new ArrayList<>();		
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("comment", group_comment_list.get(i));
			map.put("user", yh_userDAO.selectUserByUserId(group_comment_list.get(i).getUser_id()));
			map.put("tag_list", yh_group_comment_tagDAO.getTagByGroupCommentId(group_comment_list.get(i).getGroup_comment_id()));
			
			comment_list.add(map);
		}
		//GroupComment List 전송
		request.addAttribute("comment_list", comment_list);
		
		ArrayList<Integer> event_union_event_id_list = yh_event_unionDAO.getEventIdByGroupId(group_id);
		ArrayList<Event> event_union_event_list = yh_eventDAO.selectEventByEventIdList(event_union_event_id_list);
		//EventUnion List 전송
		request.addAttribute("event_union_event_list", event_union_event_list);
		
		return "group/viewGroupForm";
	}
	@RequestMapping(value = "listGroupAttendanceForm", method = RequestMethod.GET)
	public String listGroupAttendanceForm
	(
			HttpSession session,
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_id
			)
	{
		String user_id = (String)session.getAttribute("user_id"); 
		if(user_id != null)
		{
			GroupAttendance group_attendance = yh_group_attendanceDAO.selectGroupAttendanceByGroupIdUserId(user_id, group_id);
			request.addAttribute("group_attendance", group_attendance);
		}
		else
		{
			request.addAttribute("group_attendance", null);
		}
		
		GroupCategory group_category = yh_group_categoryDAO.selectGroupCategoryByGroupCategoryId(group_category_id);
		//GroupCategory 전송
		request.addAttribute("group_category", group_category);
		
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
		
		User leader = yh_userDAO.selectUserByUserId(group.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<String> group_attendance_id_list = yh_group_attendanceDAO.getUserIdByGroupId(group_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(group_attendance_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);
		
		return "group/listGroupAttendanceForm";
	}
	@RequestMapping(value = "listGroupCommentForm", method = RequestMethod.GET)
	public String listGroupCommentForm
	(
			HttpSession session,
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_id
			)
	{
		String user_id = (String)session.getAttribute("user_id"); 
		if(user_id != null)
		{
			GroupAttendance group_attendance = yh_group_attendanceDAO.selectGroupAttendanceByGroupIdUserId(user_id, group_id);
			request.addAttribute("group_attendance", group_attendance);
		}
		else
		{
			request.addAttribute("group_attendance", null);
		}
		
		GroupCategory group_category = yh_group_categoryDAO.selectGroupCategoryByGroupCategoryId(group_category_id);
		//GroupCategory 전송
		request.addAttribute("group_category", group_category);
		
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
				
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(group_id);		
		ArrayList<HashMap<String, Object>> comment_list = new ArrayList<>();		
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("comment", group_comment_list.get(i));
			map.put("user", yh_userDAO.selectUserByUserId(group_comment_list.get(i).getUser_id()));
			map.put("tag_list", yh_group_comment_tagDAO.getTagByGroupCommentId(group_comment_list.get(i).getGroup_comment_id()));
			
			comment_list.add(map);
		}
		//GroupComment List 전송
		request.addAttribute("comment_list", comment_list);
		
		return "group/listGroupCommentForm";
	}
	@RequestMapping(value = "listGroupAlbumForm", method = RequestMethod.GET)
	public String listGroupAlbumForm
	(
			HttpSession session,
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			)
	{		
		GroupCategory group_category = yh_group_categoryDAO.selectGroupCategoryByGroupCategoryId(group_category_id);
		//GroupCategory 전송
		request.addAttribute("group_category", group_category);
		
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByGroupId(group_id);
		ArrayList<HashMap<String, Object>> image_list = new ArrayList<>();		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("image", event_schedule_image_list.get(i));
			
			image_list.add(map);
		}
		//EventScheduleImage List 전송
		request.addAttribute("image_list", image_list);

		return "group/listGroupAlbumForm";
	}
}
