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
import project.ppaya.square.vo.EventSchedule;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupHashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
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
public String shtest5(Locale locale, Model model) {
	
	
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
	
	//Event로부터 EventSchedule 받아온 후, 해당 EventSchedule로부터 위치 받와서 맵 띄우기
	EventSchedule temp_es;
	
	
	
	User leader = yh_userDAO.selectUserByUserId(event.getUser_id());
	request.addAttribute("leader", leader);
	
	return "group/viewGroupEventForm";
}


}
