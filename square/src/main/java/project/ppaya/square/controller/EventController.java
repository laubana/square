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

@Controller
public class EventController
{	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
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
	YHGroupCommentDAO yh_group_boardDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	
	@RequestMapping(value = "viewGroupEventForm", method = RequestMethod.GET)
	public String viewGroupEventForm
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
		request.addAttribute("leader", leader);
		
		return "group/viewGroupEventForm";
	}
	@RequestMapping(value = "listGroupEventForm", method = RequestMethod.GET)
	public String listGroupEventForm
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
		//GroupEvent List 전송
		request.addAttribute("event_list", event_list);
		
		return "group/listGroupEventForm";
	}
}
