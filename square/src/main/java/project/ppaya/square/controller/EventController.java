package project.ppaya.square.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventComment;
import project.ppaya.square.vo.EventSchedule;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupHashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.yhdao.YHEventAttendanceDAO;
import project.ppaya.square.yhdao.YHEventCommentDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHGroupCommentDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHGroupHashtagDAO;
import project.ppaya.square.yhdao.YHUserDAO;

@Controller
public class EventController
{	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHEventAttendanceDAO yh_event_attendanceDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventCommentDAO yh_event_commentDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHGroupCommentDAO yh_group_commentDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;

	@RequestMapping(value = "createEventForm", method = RequestMethod.GET)
	public String createEventForm
	(
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			//int group_id,
			Model request
			)
	{
		return "event/createEventForm";
	}
	@RequestMapping(value = "listEventForm", method = RequestMethod.GET)
	public String listEventForm
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
		
		ArrayList<Event> event_list = yh_eventDAO.selectEventByGroupId(group_id);
		//Event List 전송
		request.addAttribute("event_list", event_list);
		
		return "event/listEventForm";
	}
	@RequestMapping(value = "viewEventForm", method = RequestMethod.GET)
	public String viewEventForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id
			//int group_id,
			//int event_id
			)
	{
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
		
		Event event = yh_eventDAO.selectEventByEventId(event_id);
		//Event 전송
		request.addAttribute("event", event);
		
		User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<String> user_id_list = yh_event_attendanceDAO.getUserIdByEventId(event_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(user_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);
		
		ArrayList<EventSchedule> event_schedule_list = yh_event_scheduleDAO.selectEventScheduleByEventId(event_id);
		//EventSchedule List 전송
		request.addAttribute("event_schedule_list", event_schedule_list);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventId(event_id);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		//Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<EventComment> event_comment_list = yh_event_commentDAO.selectEventCommentByEventId(event_id);
		for(int i = 0; i < event_comment_list.size(); i++)
		{
			event_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(event_comment_list.get(i).getUser_id()));
		}
		//EventComment List 전송
		request.addAttribute("event_comment_list", event_comment_list);
		
		//event_place 임시 전송
		String cood = "{lat: 37.566535, lng: 126.97796919999996}";
		request.addAttribute(cood,"event_place");
		
		return "event/viewEventForm";
	}
	@RequestMapping(value = "listEventAttendanceForm", method = RequestMethod.GET)
	public String listEventAttendanceForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id
			//int group_id,
			//int event_id
			)
	{
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
		
		Event event = yh_eventDAO.selectEventByEventId(event_id);		
		User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<String> event_attendance_id_list = yh_event_attendanceDAO.getUserIdByEventId(event_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(event_attendance_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);

		return "event/listEventAttendanceForm";
	}
	@RequestMapping(value = "listEventCommentForm", method = RequestMethod.GET)
	public String listEventCommentForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id
			//int group_id,
			//int event_id
			)
	{
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		//GroupHashtag List 전송
		request.addAttribute("group_hashtag_list", group_hashtag_list);
		
		ArrayList<EventComment> event_comment_list = yh_event_commentDAO.selectEventCommentByEventId(event_id);
		for(int i = 0; i < event_comment_list.size(); i++)
		{
			event_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(event_comment_list.get(i).getUser_id()));
		}
		//EventComment List 전송
		request.addAttribute("event_comment_list", event_comment_list);
		
		return "event/listEventCommentForm";
	}
	@RequestMapping(value = "listEventAlbumForm", method = RequestMethod.GET)
	public String listEventAlbumForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id
			//int group_id,
			//int event_id
			)
	{
		return "event/listEventAlbumForm";
	}
}
