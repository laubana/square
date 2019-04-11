package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventAttendance;
import project.ppaya.square.vo.EventComment;
import project.ppaya.square.vo.EventSchedule;
import project.ppaya.square.vo.EventScheduleAttendance;
import project.ppaya.square.vo.EventScheduleComment;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.EventScheduleUserSchedule;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.vo.GroupCategory;
import project.ppaya.square.vo.GroupHashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.yhdao.YHEventAttendanceDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleAttendanceDAO;
import project.ppaya.square.yhdao.YHEventScheduleCommentDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHEventScheduleUserScheduleDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupCategoryDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHGroupHashtagDAO;
import project.ppaya.square.yhdao.YHUserDAO;
import project.ppaya.square.yhutil.YHEventSchedeulUserScheduleUtil;

@Repository
@Controller
public class EventScheduleController
{	
	private static final Logger logger = LoggerFactory.getLogger(EventScheduleController.class);
	
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleAttendanceDAO yh_event_schedule_attendanceDAO;
	@Autowired
	YHEventScheduleCommentDAO yh_event_schedule_commentDAO;
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHGroupCategoryDAO yh_group_categoryDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventAttendanceDAO yh_event_attendanceDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleUserScheduleDAO yh_event_schedule_user_scheduleDAO;
	
	@RequestMapping(value = "createEventScheduleForm", method = RequestMethod.GET)
	public String createEventScheduleForm
	(
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			//int event_id,
			Model request
			)
	{

		return "event_schedule/createEventScheduleForm";
	}
	@RequestMapping(value = "listEventScheduleForm", method = RequestMethod.GET)
	public String listEventScheduleForm
	(
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			//int event_id,
			Model request
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
		
		ArrayList<EventSchedule> event_schedule_list = yh_event_scheduleDAO.selectEventScheduleByEventId(event_id);
		//EventSchedule List 전송
		request.addAttribute("event_schedule_list", event_schedule_list);
		
		return "event_schedule/listEventScheduleForm";
	}
	@RequestMapping(value = "viewEventScheduleForm", method = RequestMethod.GET)
	public String viewEventScheduleForm
	(
			HttpSession session,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id,
			//int event_schedule_id,
			Model request
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
				if(event_attendance != null)
				{
					EventScheduleAttendance event_schedule_attendance = yh_event_schedule_attendanceDAO.selectEventScheduleAttendanceByEventScheduleIdUserId(user_id, event_schedule_id);
					request.addAttribute("event_schedule_attendance", event_schedule_attendance);
				}
				else
				{
					request.addAttribute("event_attendance", null);
				}
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
		
		EventSchedule event_schedule = yh_event_scheduleDAO.selectEventScheduleByEventScheduleId(event_schedule_id);
		//EventSchedule 전송
		request.addAttribute("event_schedule", event_schedule);
		
		Event event = yh_eventDAO.selectEventByEventId(event_id);		
		User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<String> user_id_list = yh_event_schedule_attendanceDAO.getUserIdByEventScheduleId(event_schedule_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(user_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);
		
		ArrayList<EventScheduleComment> event_schedule_comment_list = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleId(event_schedule_id);
		for(int i = 0; i < event_schedule_comment_list.size(); i++)
		{
			event_schedule_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(event_schedule_comment_list.get(i).getUser_id()));
		}
		//EventComment List 전송
		request.addAttribute("event_schedule_comment_list", event_schedule_comment_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleId(event_schedule_id);
		//Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<HashMap<String, Object>> test_list = new ArrayList<>();
		for(int i = 0; i < user_list.size(); i++)
		{
			HashMap<String, Object> test_map = new HashMap<>();
			test_map.put("user", user_list.get(i));
			ArrayList<EventScheduleUserSchedule> temp_test_list = YHEventSchedeulUserScheduleUtil.parseIntegratedEventScheduleUserScheduleListToView(YHEventSchedeulUserScheduleUtil.integrateEventScheduleUserScheduleList(YHEventSchedeulUserScheduleUtil.cropEventScheduleUserScheduleList(yh_event_schedule_user_scheduleDAO.selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate(event_schedule_id, user_list.get(i).getUser_id(), (new Date()).getTime(), (new Date()).getTime() + 7 * 86400 * 1000), (new Date()).getTime(), (new Date()).getTime() + 7 * 86400 * 1000), user_list.get(i).getUser_id(), event_schedule_id), user_list.get(i).getUser_id(), event_schedule_id, (new Date()).getTime(), (new Date()).getTime() + 7 * 86400 * 1000)  ;
			test_map.put("list", temp_test_list);
			
			test_list.add(test_map);
		}
		
		logger.debug("{}", new JSONArray(test_list).toString(2));
		
		request.addAttribute("json_event_schedule_user_schedule_list_list", new JSONArray(test_list));
		request.addAttribute("event_schedule_user_schedule_list_list", test_list);
		
		return "event_schedule/viewEventScheduleForm";
	}
	@RequestMapping(value = "listEventScheduleAttendanceForm", method = RequestMethod.GET)
	public String listEventScheduleAttendanceForm
	(
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id
			//int group_id,
			//int event_id
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
		
		Event event = yh_eventDAO.selectEventByEventId(event_id);		
		User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<String> user_id_list = yh_event_schedule_attendanceDAO.getUserIdByEventScheduleId(event_schedule_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(user_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);
		
		return "event_schedule/listEventScheduleAttendanceForm";
	}
	@RequestMapping(value = "listEventScheduleCommentForm", method = RequestMethod.GET)
	public String listEventScheduleCommentForm
	(
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id
			//int group_id,
			//int event_id
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
		
		ArrayList<EventScheduleComment> event_schedule_comment_list = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleId(event_schedule_id);
		for(int i = 0; i < event_schedule_comment_list.size(); i++)
		{
			event_schedule_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(event_schedule_comment_list.get(i).getUser_id()));
		}
		//EventComment List 전송
		request.addAttribute("event_schedule_comment_list", event_schedule_comment_list);
		
		return "event_schedule/listEventScheduleCommentForm";
	}
	@RequestMapping(value = "listEventScheduleAlbumForm", method = RequestMethod.GET)
	public String listEventScheduleAlbumForm
	(
			Model request,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id
			//int group_id,
			//int event_id
			)
	{
		
		return "event_schedule/listEventScheduleAlbumForm";
	}
}
