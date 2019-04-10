package project.ppaya.square.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.shdao.SH_DAO_User;
import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupComment;
import project.ppaya.square.vo.GroupHashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupCommentDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHGroupHashtagDAO;
import project.ppaya.square.yhdao.YHUserDAO;

@Repository
@Controller
public class GroupController
{	
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	YHUserDAO yh_userDAO;
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
	YHGroupCommentDAO yh_group_commentDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	
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
		//GroupCategoryId 전송
		request.addAttribute("group_category_id", group_category_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupCategoryId(group_category_id);
		//Group List 전송
		request.addAttribute("group_list", group_list);

		return "group/listGroupForm";
	}
	@RequestMapping(value = "viewGroupForm", method = RequestMethod.GET)
	public String viewGroupForm
	(
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_category_id
			//int group_id
			)
	{
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
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(group_id);
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		//Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(group_id);
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			group_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(group_comment_list.get(i).getUser_id()));
		}
		//GroupComment List 전송
		request.addAttribute("group_comment_list", group_comment_list);
		
		return "group/viewGroupForm";
	}
	@RequestMapping(value = "listGroupAttendanceForm", method = RequestMethod.GET)
	public String listGroupAttendanceForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_id
			)
	{
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
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_id
			)
	{
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
				
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(group_id);
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			group_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(group_comment_list.get(i).getUser_id()));
		}
		//GroupComment List 전송
		request.addAttribute("group_comment_list", group_comment_list);
		
		return "group/listGroupCommentForm";
	}
	@RequestMapping(value = "listGroupAlbumForm", method = RequestMethod.GET)
	public String photoForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_id
			)
	{
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(group_id);
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		//Group의 Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);

		return "group/listGroupAlbumForm";
	}
}
