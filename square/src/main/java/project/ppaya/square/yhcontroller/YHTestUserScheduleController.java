package project.ppaya.square.yhcontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.vo.EventScheduleUserSchedule;
import project.ppaya.square.yhdao.YHEventScheduleUserScheduleDAO;
import project.ppaya.square.yhdao.YHUserDAO;

/**
 * Handles requests for the application home page.
 */
@Repository
@Controller
public class YHTestUserScheduleController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestUserScheduleController.class);
	@Autowired
	YHUserDAO yh_usertDAO;
	@Autowired
	YHEventScheduleUserScheduleDAO yh_event_schedule_user_scheduleDAO;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/eventFormByClient", method = RequestMethod.GET)
	public String eventFormByClientGET()
	{
		int event_schedule_id = 1;
		String user_id = "id1";
		
		ArrayList<EventScheduleUserSchedule> event_schedule_user_schedule_list = yh_event_schedule_user_scheduleDAO.selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate(event_schedule_id, user_id, (new Date()).getTime(), (new Date()).getTime() + 24 * 3600 * 1000);
	
		for(int i = 0; i < event_schedule_user_schedule_list.size(); i++)
		{
			logger.debug("{}", event_schedule_user_schedule_list.get(i).getUser_schedule_id());
		}
		
		
		return "eventFormByClient";
	}
	/*@ResponseBody
	@RequestMapping(value = "/getMultipleIntegrateScheduleMapListAction", method = RequestMethod.POST)
	public HashMap<String, Object> getMultipleIntegrateScheduleMapListActionPOST(int event_num, String[] attendee_list, String start_period, String end_period)
	{
		logger.debug("getMultipleIntegrateScheduleMapListActionPOST");
		
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<ArrayList<Schedule>> multiple_integrate_schedule_list = new ArrayList<>();
		for(int i = 0; i < attendee_list.length; i++)
		{
			multiple_integrate_schedule_list.add(integrateSeparateScheduleList(cropScheduleList(getSingleSeparateScheduleList(attendee_list[i], "2019-03-03T00:00", "2019-03-04T00:00"), "2019-03-03T07:30", "2019-03-04T00:00"), attendee_list[i]));			
		}
		map.put("multiple_integrate_schedule_list", multiple_integrate_schedule_list);
		
		ArrayList<ArrayList<Schedule>> multiple_separate_schedule_list = new ArrayList<>();
		for(int i = 0; i < attendee_list.length; i++)
		{
			multiple_separate_schedule_list.add(cropScheduleList(getSingleSeparateScheduleList(attendee_list[i], "2019-03-03T00:00", "2019-03-04T00:00"), "2019-03-03T07:30", "2019-03-04T00:00"));			
		}
		map.put("multiple_separate_schedule_list", multiple_separate_schedule_list);
		
		return map;
	}*/
}
