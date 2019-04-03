package project.ppaya.square.yhcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Handles requests for the application home page.
 */
@Controller
public class _YHUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(_YHUserController.class);
	
	/*@Autowired
	GroupAttendanceDAO group_attendanceDAO;
	@Autowired
	GroupDAO groupDAO;
	@Autowired
	EventDAO eventDAO;
	@Autowired
	EventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	EventScheduleAttendanceDAO event_schedule_attendanceDAO;
	@Autowired
	EventScheduleDAO event_scheduleDAO;
	@Autowired
	EventScheduleImageFaceDAO event_schedule_image_faceDAO;
	@Autowired
	AlbumDAO albumDAO;
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = "/userForm", method = RequestMethod.GET)
	public String loginFormGET(HttpSession session)
	{
		User user = userDAO.selectUserByUserId((String)session.getAttribute("user_id"));
		String user_id = user.getUser_id();
		String image_id = user.getImage_id();
		
		ArrayList<Integer> event_schedule_id_list = event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		albumDAO.deleteAlbumByNotEventScheduleImageIdUserId(event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < event_schedule_image_id_list.size(); i++)
		{
			albumDAO.insertAlbum(event_schedule_image_id_list.get(i), user_id);
		}
		
		albumDAO.updateSelfByUserId(user_id);
		
		MSFaceUtil.updateEventScheduleImageFaceByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_face_id_list = event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id = MSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(event_schedule_image_face_id_list, MSFaceUtil.detectFace(Reference.file_path, image_id));
		
		ArrayList<String> similar_event_schedule_image_id_list = event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id);
		
		albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);	
		
		return "userForm";
	}*/
}
