package project.ppaya.square.yhaction;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;

@Repository
@Controller
public class YHattendEventScheduleAction
{	
	private static final Logger logger = LoggerFactory.getLogger(YHattendEventScheduleAction.class);

	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHEventScheduleUserScheduleDAO yh_event_schedule_user_scheduleDAO;
	
	/*@ResponseBody
	@RequestMapping(value = "/insertEventScheduleUserScheduleAction", method = RequestMethod.POST)
	public void insertEventScheduleUserScheduleAction
	(
			HttpSession session,
			@RequestBody ArrayList<EventScheduleUserSchedule> event_schedule_user_schedule_list,
			int event_schedule_id
			)
	{
		String user_id = "id1";
		
		yh_event_schedule_user_scheduleDAO.deleteEventScheduleUserScheduleByUserIdEventScheduleId(user_id, event_schedule_id);
		
		for(int i = 0; i < event_schedule_user_schedule_list.size(); i++)
		{
			event_schedule_user_schedule_list.get(i).setUser_id(user_id);
			
			yh_event_schedule_user_scheduleDAO.insertEventScheduleUserSchedule(event_schedule_user_schedule_list.get(i));
		}
	}*/
}
