package project.ppaya.square.yhcontroller;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.EventScheduleUserSchedule;
import project.ppaya.square.yhdao.YHEventScheduleAttendanceDAO;
import project.ppaya.square.yhdao.YHEventScheduleUserScheduleDAO;
import project.ppaya.square.yhdao.YHUserDAO;
import project.ppaya.square.yhutil.YHEventSchedeulUserScheduleUtil;
import project.ppaya.square.yhutil.YHVideoIndexerUtil;

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
	@Autowired
	YHEventScheduleAttendanceDAO yh_event_schedule_attendanceDAO;
	
	@RequestMapping(value = "/eventFormByClient", method = RequestMethod.GET)
	public void eventFormByClientGET()
	{
		int event_schedule_id = 1;
		long start_date = Long.parseLong("1554649200000");
		long end_date = Long.parseLong("1554735600000");
		
		ArrayList<String> user_id_list = yh_event_schedule_attendanceDAO.getUserIdByEventScheduleId(event_schedule_id);
		
		ArrayList<ArrayList<EventScheduleUserSchedule>> integrated_event_schedule_user_schedule_list_list = new ArrayList<>();
		
		for(int i = 0; i < user_id_list.size(); i++)
		{
			integrated_event_schedule_user_schedule_list_list.add(YHEventSchedeulUserScheduleUtil.integrateEventScheduleUserScheduleList(YHEventSchedeulUserScheduleUtil.cropEventScheduleUserScheduleList(yh_event_schedule_user_scheduleDAO.selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate(event_schedule_id, user_id_list.get(i), start_date, end_date), start_date, end_date), user_id_list.get(i), 1));
		}	
		
		ArrayList<EventScheduleUserSchedule> integrated_event_schedule_user_schedule_list = YHEventSchedeulUserScheduleUtil.integrateEventScheduleUserScheduleListList(integrated_event_schedule_user_schedule_list_list, event_schedule_id);
		
		ArrayList<EventScheduleUserSchedule> view = YHEventSchedeulUserScheduleUtil.parseIntegratedEventScheduleUserScheduleListToView(integrated_event_schedule_user_schedule_list, "", event_schedule_id, start_date, end_date);
		
		for(int i = 0; i < view.size(); i++)
		{
			logger.debug("{}", view.get(i));
		}
	}
	@RequestMapping(value = "/yhtest", method = RequestMethod.GET)
	public void yhtest()
	{
		JSONObject jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex("3a9c7198af"));
		JSONArray jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
		logger.debug("{}", jsonArray.length());
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
