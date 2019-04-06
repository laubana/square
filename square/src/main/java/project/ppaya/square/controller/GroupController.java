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

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.shdao.SH_DAO_User;
import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.vo.GroupBoard;
import project.ppaya.square.vo.User;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupBoardDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
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
	YHGroupBoardDAO yh_group_boardDAO;
	
	@Autowired
	SH_DAO_Group sh_gdao;
	@Autowired
	SH_DAO_User sh_udao;
	@RequestMapping(value = "groupSearch", method = RequestMethod.GET)
	public String searchForm(
			String category
			, Model request) {
		
		String testint = "1"; //나중에 jsp에서 값 받을 거. TABLE_G 테이블의 group_category_id 찾을 값.
		int ctg = Integer.parseInt(testint);
		ArrayList<Group> glist = new ArrayList<>();
		glist = sh_gdao.getGroupByCategory(ctg);
		request.addAttribute("glist", glist);
		logger.info("그룹탐색입니다!");

		return "group/groupSearchForm";
	}
	@RequestMapping(value = "groupCreateForm", method = RequestMethod.GET)
	public String createGroupForm(Model request)
	{
		return "group/groupCreateForm";
	}
	@RequestMapping(value = "groupMain", method = RequestMethod.GET)
	public String mainForm(Model request, int group_id)
	{
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		//Group 전송
		request.addAttribute("group", group);
		
		ArrayList<Event> event_list = yh_eventDAO.selectEventByGroupId(group_id);
		//Group의 Event List 전송
		request.addAttribute("event_list", event_list);
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(group_id);
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		//Group의 Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<String> user_id_list = yh_group_attendanceDAO.getUserIdByGroupId(group_id);
		ArrayList<User> user_list = yh_userDAO.selectUserByUserIdList(user_id_list);
		//Group의 User List 전송
		request.addAttribute("user_list", user_list);
		
		ArrayList<GroupBoard> group_board_list = yh_group_boardDAO.selectGroupBoardByGroupId(group_id);
		//Group의 Board List 전송
		request.addAttribute("group_board_list", group_board_list);
		
		return "group/groupMainForm";
	}
	@RequestMapping(value = "groupPhoto", method = RequestMethod.GET)
	public String photoForm(Model request, int group_id)
	{
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(group_id);
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		//Group의 Image List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);

		return "group/groupPhotoForm";
	}
}
