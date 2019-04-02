package project.ppaya.square.yhcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Handles requests for the application home page.
 */
@Controller
public class _GroupController
{	
	private static final Logger logger = LoggerFactory.getLogger(_GroupController.class);
	
	/*@Autowired
	UserDAO userDAO;
	@Autowired
	EventDAO eventDAO;
	@Autowired
	EventScheduleDAO event_scheduleDAO;
	@Autowired
	EventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	EventScheduleImageFaceDAO event_schedule_image_faceDAO;
	
	@RequestMapping(value = "/groupForm", method = RequestMethod.GET)
	public String groupFormGET(HttpSession session, Model request, int group_id)
	{	
		User user = userDAO.selectUserByUserId((String)session.getAttribute("user_id"));
		String user_id = user.getUser_id();
		String image_id = user.getImage_id();
		ArrayList<Integer> event_id_list = eventDAO.getEventIdByGroupId(group_id);
		
		ArrayList<Integer> event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		request.addAttribute("event_schedule_image_id_list", event_schedule_image_id_list);
		
		ArrayList<String> event_schedule_image_face_id_list = event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id_list = MSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(event_schedule_image_face_id_list, MSFaceUtil.detectFace(Reference.file_path, image_id));
		
		ArrayList<String> similar_event_schedule_image_id_list = event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id_list);		
		request.addAttribute("similar_event_schedule_image_id_list", similar_event_schedule_image_id_list);
		
		return "groupForm";
	}*/
}
