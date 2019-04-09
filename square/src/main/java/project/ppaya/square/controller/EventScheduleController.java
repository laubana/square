package project.ppaya.square.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventScheduleController
{	
	private static final Logger logger = LoggerFactory.getLogger(EventScheduleController.class);
	
	@RequestMapping(value = "createEventScheduleForm", method = RequestMethod.GET)
	public String createEventScheduleForm
	(
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
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			//int event_id,
			Model request
			)
	{

		return "event_schedule/listEventScheduleForm";
	}
	@RequestMapping(value = "viewEventScheduleForm", method = RequestMethod.GET)
	public String viewEventScheduleForm
	(
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id,
			//int event_schedule_id,
			Model request
			)
	{

		return "event_schedule/viewEventScheduleForm";
	}
	@RequestMapping(value = "listEventScheduleAttendanceForm", method = RequestMethod.GET)
	public String listEventScheduleAttendanceForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id
			//int group_id,
			//int event_id
			)
	{
		return "event_schedule/listEventScheduleAttendanceForm";
	}
	@RequestMapping(value = "listEventScheduleCommentForm", method = RequestMethod.GET)
	public String listEventScheduleCommentForm
	(
			Model request,
			@RequestParam(value = "group_id", defaultValue = "1") int group_id,
			@RequestParam(value = "event_id", defaultValue = "1") int event_id,
			@RequestParam(value = "event_schedule_id", defaultValue = "1") int event_schedule_id
			//int group_id,
			//int event_id
			)
	{
		return "event_schedule/listEventScheduleCommentForm";
	}
	@RequestMapping(value = "listEventScheduleAlbumForm", method = RequestMethod.GET)
	public String listEventScheduleAlbumForm
	(
			Model request,
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
