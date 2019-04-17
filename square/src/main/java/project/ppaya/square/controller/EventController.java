package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;

@Controller
public class EventController
{	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupCategoryDAO yh_group_categoryDAO;
	@Autowired
	YHEventAttendanceDAO yh_event_attendanceDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventCommentDAO yh_event_commentDAO;
	@Autowired
	YHEventCommentTagDAO yh_event_comment_tagDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHGroupCommentDAO yh_group_commentDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	@Autowired
	YHEventUnionDAO yh_event_unionDAO;

	@RequestMapping(value = "createEventForm", method = RequestMethod.GET)
	public String createEventForm
	(
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			//int group_id,
			Model request
			)
	{
		//event_place 임시 전송
		ArrayList<String> place_list = new ArrayList<>();
		place_list.add("'東京　京橋駅'");
		place_list.add("'名古屋　愛知県'");
		place_list.add("'大阪市 大正区'");
		place_list.add("'仙台市　宮城県'");
		place_list.add("'名古屋　愛知県'");
		request.addAttribute("place_list", place_list);
		
		
		return "event/createEventForm";
	}
	@RequestMapping(value = "listEventForm", method = RequestMethod.GET)
	public String listEventForm
	(
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id
			//int group_id
			)
	{
		
		GroupCategory group_category = yh_group_categoryDAO.selectGroupCategoryByGroupCategoryId(group_category_id);
		//GroupCategory 전송
		request.addAttribute("group_category", group_category);
		
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
			HttpSession session,
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id
			//int group_id,
			//int event_id
			)
	{
		String user_id = (String)session.getAttribute("user_id"); 
		if(user_id != null)
		{
			GroupAttendance group_attendance = yh_group_attendanceDAO.selectGroupAttendanceByGroupIdUserId(user_id, group_id);
			request.addAttribute("group_attendance", group_attendance);
			if(group_attendance != null)
			{
				EventAttendance event_attendance = yh_event_attendanceDAO.selectEventAttendanceByEventIdUserId(user_id, event_id);
				request.addAttribute("event_attendance", event_attendance);
			}
			else
			{
				request.addAttribute("event_attendance", null);
			}
		}
		else
		{
			request.addAttribute("group_attendance", null);
			request.addAttribute("event_attendance", null);
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
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		//Video List 전송
		request.addAttribute("video_list", event_schedule_video_list);
		
		ArrayList<EventComment> event_comment_list = yh_event_commentDAO.selectEventCommentByEventId(event_id);
		
		ArrayList<HashMap<String, Object>> comment_list = new ArrayList<>();
		
		for(int i = 0; i < event_comment_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("comment", event_comment_list.get(i));
			map.put("user", yh_userDAO.selectUserByUserId(event_comment_list.get(i).getUser_id()));
			map.put("tag_list", yh_event_comment_tagDAO.getTagByEventCommentId(event_comment_list.get(i).getEvent_comment_id()));
			
			comment_list.add(map);
		}
		//EventComment List 전송
		request.addAttribute("comment_list", comment_list);
		
		//event_place 임시 전송
		String place = "東京　京橋駅";
		request.addAttribute("event_place", place);
		
		ArrayList<Integer> group_union_id_list = yh_event_unionDAO.getGroupIdByEventId(event_id);
		ArrayList<Group> group_union_list = yh_groupDAO.selectGroupByGroupIdListNotGroupId(group_union_id_list, group_id);
		//GroupUnion List 전송
		request.addAttribute("group_union_list", group_union_list);
		
		return "event/viewEventForm";
	}
	@RequestMapping(value = "listEventAttendanceForm", method = RequestMethod.GET)
	public String listEventAttendanceForm
	(
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
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
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
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
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id
			//int group_id,
			//int event_id
			)
	{
		return "event/listEventAlbumForm";
	}
}
