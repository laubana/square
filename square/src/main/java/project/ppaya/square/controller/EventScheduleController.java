package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

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
	YHEventScheduleCommentTagDAO yh_event_schedule_comment_tagDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHEventScheduleUserScheduleDAO yh_event_schedule_user_scheduleDAO;
	
	@RequestMapping(value = "createEventScheduleForm", method = RequestMethod.GET)
	public String createEventScheduleForm
	(
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			//int event_id,
			Model request,
			HttpSession session
			)
	{
		User user = yh_userDAO.selectUserByUserId((String)session.getAttribute("user_id"));
		//User 전송
		request.addAttribute("user", user);
		
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
		
		ArrayList<EventSchedule> event_schedule_list = yh_event_scheduleDAO.selectEventScheduleByEventId(event_id);
		//EventSchedule List 전송
		request.addAttribute("event_schedule_list", event_schedule_list);
		
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

	@RequestMapping(value = "joinEventScheduleForm", method = RequestMethod.GET)
	public String joinEventScheduleForm
	(
			HttpSession session,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id,
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
				
		EventSchedule event_schedule = yh_event_scheduleDAO.selectEventScheduleByEventScheduleId(event_schedule_id);
		//EventSchedule 전송
		request.addAttribute("event_schedule", event_schedule);
		
		Event event = yh_eventDAO.selectEventByEventId(event_id);		
		//Event 전송
		request.addAttribute("event", event);
		
		User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		return "event_schedule/joinEventScheduleForm";
	}
	@RequestMapping(value = "viewEventScheduleForm", method = RequestMethod.GET)
	public String viewEventScheduleForm
	(
			HttpSession session,
			@RequestParam(value = "group_category_id", defaultValue = "1") int group_category_id,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id,
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
		//Event 전송
		request.addAttribute("event", event);
		
		User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
		//Leader 전송
		request.addAttribute("leader", leader);
		
		ArrayList<String> user_id_list = yh_event_schedule_attendanceDAO.getUserIdByEventScheduleId(event_schedule_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(user_id_list);
		//User List 전송
		request.addAttribute("user_list", user_list);
		
		ArrayList<EventScheduleComment> event_schedule_comment_list = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleId(event_schedule_id);		
		ArrayList<HashMap<String, Object>> comment_list = new ArrayList<>();		
		for(int i = 0; i < event_schedule_comment_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("comment", event_schedule_comment_list.get(i));
			map.put("user", yh_userDAO.selectUserByUserId(event_schedule_comment_list.get(i).getUser_id()));
			map.put("tag_list", yh_event_schedule_comment_tagDAO.getTagByEventScheduleCommentId(event_schedule_comment_list.get(i).getEvent_schedule_comment_id()));
			
			comment_list.add(map);
		}
		//EventScheduleComment List 전송
		request.addAttribute("comment_list", comment_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleId(event_schedule_id);
		//Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleId(event_schedule_id);
		//Video List 전송
		request.addAttribute("video_list", event_schedule_video_list);
		
		Calendar current_date = Calendar.getInstance();
		current_date.set(Calendar.SECOND, 0);
		current_date.set(Calendar.MILLISECOND, 0);
		
		long from_date = current_date.getTimeInMillis();
		long to_date = new Date(from_date + 86400000 * 7).getTime();
		
		ArrayList<HashMap<String, Object>> user_view_list_list = new ArrayList<>();
		ArrayList<ArrayList<EventScheduleUserSchedule>> integrate_event_schedule_user_schedule_list_list = new ArrayList<>();
		for(int i = 0; i < user_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			
			ArrayList<EventScheduleUserSchedule> event_schedule_user_schedule_list = yh_event_schedule_user_scheduleDAO.selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate(event_schedule_id, user_list.get(i).getUser_id(), from_date, to_date);
			ArrayList<EventScheduleUserSchedule> crop_event_schedule_user_schedule_list = YHEventSchedeulUserScheduleUtil.cropEventScheduleUserScheduleList(event_schedule_user_schedule_list, from_date, to_date);
			ArrayList<EventScheduleUserSchedule> integrate_event_schedule_user_schedule_list = YHEventSchedeulUserScheduleUtil.integrateEventScheduleUserScheduleList(crop_event_schedule_user_schedule_list, user_list.get(i).getUser_id(), event_schedule_id);
			integrate_event_schedule_user_schedule_list_list.add(integrate_event_schedule_user_schedule_list);
			ArrayList<EventScheduleUserSchedule> integrate_view_list = YHEventSchedeulUserScheduleUtil.parseIntegratedEventScheduleUserScheduleListToView(integrate_event_schedule_user_schedule_list, user_list.get(i).getUser_id(), event_schedule_id, from_date, to_date);
			map.put("user", user_list.get(i));
			map.put("list", integrate_view_list);
			
			user_view_list_list.add(map);
		}
		
		request.addAttribute("json_event_schedule_user_schedule_list_list", new JSONArray(user_view_list_list));
		request.addAttribute("event_schedule_user_schedule_list_list", user_view_list_list);
		
		ArrayList<EventScheduleUserSchedule> event_schedule_attendace_count_list = new ArrayList<>();
		for(long i = from_date; i < to_date; i += 60000)
		{
			int count = user_list.size();
			
			for(int j = 0; j < integrate_event_schedule_user_schedule_list_list.size(); j++)
			{
				if(YHEventSchedeulUserScheduleUtil.isExistPeriodInEventScheduleUserScheduleList(integrate_event_schedule_user_schedule_list_list.get(j), i, i + 3600000) == true)
				{
					count--;
				}
			}
			
			event_schedule_attendace_count_list.add(new EventScheduleUserSchedule("", 0, i, i + 60000, count));
		}
		
		ArrayList<EventScheduleUserSchedule> integrate_event_schedule_attendace_count_list = new ArrayList<>();
		long start_date = -1;
		long end_date = -1;
		int count = -1;
		for(int i = 0; i < event_schedule_attendace_count_list.size();)
		{
			if(start_date == -1 || end_date == -1)
			{
				start_date = event_schedule_attendace_count_list.get(i).getStart_date();
				end_date = event_schedule_attendace_count_list.get(i).getEnd_date();
				count = event_schedule_attendace_count_list.get(i).getTypeof();
			}
			
			if(count != event_schedule_attendace_count_list.get(i).getTypeof())
			{
				integrate_event_schedule_attendace_count_list.add(new EventScheduleUserSchedule("", 0, start_date, end_date, count));
				start_date = -1;
				end_date = -1;
				count = -1;
			}
			else
			{
				end_date = event_schedule_attendace_count_list.get(i).getEnd_date();
				i++;
			}
		}
		integrate_event_schedule_attendace_count_list.add(new EventScheduleUserSchedule("", 0, start_date, end_date, count));
		
		logger.debug("{}", integrate_event_schedule_attendace_count_list.toString());
		
		//HashMap<String, Object> event_schedule_attendace_count_list_map = new HashMap<>();
		//event_schedule_attendace_count_list_map.put("list", integrate_event_schedule_attendace_count_list);
		request.addAttribute("event_schedule_attendace_count_list", integrate_event_schedule_attendace_count_list);
		
		request.addAttribute("json_integrate_event_schedule_attendace_count_list", new JSONArray(integrate_event_schedule_attendace_count_list));
		//맵스 장소 보내기
		String place = "東京　京橋駅";
		request.addAttribute("place",place);

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
		ArrayList<HashMap<String, Object>> comment_list = new ArrayList<>();		
		for(int i = 0; i < event_schedule_comment_list.size(); i++)
		{
			HashMap<String, Object> map = new HashMap<>();
			map.put("comment", event_schedule_comment_list.get(i));
			map.put("user", yh_userDAO.selectUserByUserId(event_schedule_comment_list.get(i).getUser_id()));
			map.put("tag_list", yh_event_schedule_comment_tagDAO.getTagByEventScheduleCommentId(event_schedule_comment_list.get(i).getEvent_schedule_comment_id()));
			
			comment_list.add(map);
		}
		//EventScheduleComment List 전송
		request.addAttribute("comment_list", comment_list);
		
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
