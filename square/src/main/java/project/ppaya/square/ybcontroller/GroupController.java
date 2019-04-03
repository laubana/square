package project.ppaya.square.ybcontroller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.vo.GroupBoard;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupBoardDAO;
import project.ppaya.square.yhdao.YHGroupDAO;

@Repository
@Controller
public class GroupController
{
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	YHGroupDAO groupDAO;
	@Autowired
	YHGroupAttendanceDAO group_attendanceDAO;
	@Autowired
	YHEventDAO eventDAO;
	@Autowired
	YHEventScheduleDAO event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	YHGroupBoardDAO group_boardDAO;
	
	@RequestMapping(value = "groupSearch", method = RequestMethod.GET)
	public String searchForm() {
		logger.info("그룹탐색입니다!");

		return "group/groupSearchForm";
	}
	
	@RequestMapping(value = "groupMain", method = RequestMethod.GET)
	public String mainForm(Model model, int group_id)
	{
		logger.info("그룹탐색입니다!");

		Group group = groupDAO.selectGroupByGroupId(group_id);
		model.addAttribute("group", group);
		
		ArrayList<Event> event_list = eventDAO.selectEventByGroupId(group_id);
		model.addAttribute("event_list", event_list);
		
		ArrayList<Integer> event_id_list = eventDAO.getEventIdByGroupId(group_id);
		ArrayList<Integer> event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		model.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<GroupAttendance> group_attendance_list = group_attendanceDAO.selectGroupAttendanceByGroupId(group_id);
		model.addAttribute("group_attendance_list", group_attendance_list);
		
		ArrayList<GroupBoard> group_board_list = group_boardDAO.selectGroupBoardByGroupId(group_id);
		model.addAttribute("group_board_list", group_board_list);
		
		return "group/groupMainForm";
	}
	
	@RequestMapping(value = "groupPhoto", method = RequestMethod.GET)
	public String photoForm() {
		logger.info("그룹탐색입니다!");

		return "group/groupPhotoForm";
	}
}
