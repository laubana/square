package project.ppaya.square.shcontroller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.shdao.SH_DAO_User;
import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventAttendance;
import project.ppaya.square.vo.EventComment;
import project.ppaya.square.vo.EventSchedule;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.vo.GroupCategory;
import project.ppaya.square.vo.GroupHashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;
import project.ppaya.square.yhdao.YHEventAttendanceDAO;
import project.ppaya.square.yhdao.YHEventCommentDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoDAO;
import project.ppaya.square.yhdao.YHEventUnionDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupCategoryDAO;
import project.ppaya.square.yhdao.YHGroupCommentDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHGroupHashtagDAO;
import project.ppaya.square.yhdao.YHUserDAO;

@Controller
public class SHTestContorller {
Logger logger = Logger.getLogger(SHTestContorller.class);
	
@Autowired
SH_DAO_Group sh_gdao;
@Autowired
SH_DAO_User sh_udao;


@RequestMapping(value = "shtest1", method = RequestMethod.GET)
public String shtest1(Locale locale, Model model) {
	
	
	return "test_sh/shtest1";
}
@RequestMapping(value = "shtest2", method = RequestMethod.GET)
public String shtest2(Locale locale, Model model) {
	
	
	return "test_sh/shtest2";
}
@RequestMapping(value = "shtest3", method = RequestMethod.GET)
public String shtest3(Locale locale, Model model) {
	
ArrayList<String> coodlist = new ArrayList<>();
coodlist.add("'광주광역시 북구'");
coodlist.add("'서울특별시 종로구'");
coodlist.add("'seoul'");
coodlist.add("'광주광역시 북구'");
coodlist.add("'강원도 춘천'");
	model.addAttribute("coodlist", coodlist);
	return "test_sh/shtest3";
}


@RequestMapping(value = "shtest4", method = RequestMethod.GET)
public String shtest4(Locale locale, Model model) {
	ArrayList<String> coodlist = new ArrayList<>();
	coodlist.add("{lat: -37.774785, lng: 145.137978}");
	coodlist.add("{lat: -37.819616, lng: 144.968119}");
	coodlist.add("{lat: -37.774785, lng: 145.137978}");
	coodlist.add("{lat: -39.927193, lng: 175.053218}");
	coodlist.add("{lat: -41.330162, lng: 174.865694}");
		model.addAttribute("coodlist", coodlist);
	
	return "test_sh/shtest4";
}
@RequestMapping(value = "shtest5", method = RequestMethod.GET)
public String shtest5(
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
		for(int i = 0; i < event_comment_list.size(); i++)
		{
			event_comment_list.get(i).setUser(yh_userDAO.selectUserByUserId(event_comment_list.get(i).getUser_id()));
		}
		//EventComment List 전송
		request.addAttribute("event_comment_list", event_comment_list);
		
		//event_place 임시 전송
		String place = "東京　京橋駅";
		request.addAttribute("event_place", place);
		
		ArrayList<Integer> group_union_id_list = yh_event_unionDAO.getGroupIdByEventId(event_id);
		ArrayList<Group> group_union_list = yh_groupDAO.selectGroupByGroupIdListNotGroupId(group_union_id_list, group_id);
		//GroupUnion List 전송
		request.addAttribute("group_union_list", group_union_list);
		
	return "test_sh/shtest5";
}
@RequestMapping(value = "shtest6", method = RequestMethod.GET)
public String shtest6(Locale locale, Model model) {
	
	
	return "test_sh/shtest6";
}
@RequestMapping(value = "shtest7", method = RequestMethod.GET)
public String shtest7(Locale locale, Model model) {
	
	
	return "test_sh/shtest7";
}
@RequestMapping(value = "shtest8", method = RequestMethod.GET)
public String shtest8(Locale locale, Model model) {
	
	
	return "test_sh/shtest8";
}




@ResponseBody
@RequestMapping(value = "groupSearchByKeyword", method = RequestMethod.GET)
public ArrayList<Group> response_GroupSearch(String keyword){
	
	ArrayList<Group> glist = null;
	glist = sh_gdao.getGroupByKeyword(keyword);
	
	int i = 0; 	Group g1 = new Group();
	for(i = 0; i <= 6; i = i + 1){
		g1.setContent("tempContent" + 1);
		g1.setName("tempName"+1);
		g1.setGroup_category_id(i);
		g1.setGroup_id(i);
		g1.setRegion("tempRegn"+i);
		glist.add(g1);
	}
	
	return glist;
}



////////////////////나중에 EventController에 복붙
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
	
	//Event로부터 EventSchedule 받아온 후, 해당 EventSchedule로부터 위치 받와서 맵 띄우기
	EventSchedule temp_es;
	
	
	
	User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
	request.addAttribute("leader", leader);
	
	return "group/viewGroupEventForm";
}


}
