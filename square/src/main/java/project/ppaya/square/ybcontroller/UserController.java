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

import project.ppaya.square.yhdao.AlbumDAO;
import project.ppaya.square.yhdao.EventDAO;
import project.ppaya.square.yhdao.EventScheduleDAO;
import project.ppaya.square.yhdao.EventScheduleImageDAO;
import project.ppaya.square.yhutil.UserFormUtil;
import project.ppaya.square.yhvo.Group;

@Repository
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserFormUtil user_formUtil;
	
	@Autowired
	EventDAO eventDAO;
	@Autowired
	EventScheduleDAO event_scheduleDAO;
	@Autowired
	EventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	AlbumDAO albumDAO;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm() {
		logger.info("로그인입니다!");

		return "member/loginForm";
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPageForm() {
		logger.info("마이페이지입니다!");
		
		return "member/myPageForm";
	}
	
	@RequestMapping(value = "memberPhoto", method = RequestMethod.GET)
	public String memberPhotoForm(Model model)
	{
		logger.info("개인앨범입니다!");
		
		String user_id = "id1";
		
		ArrayList<Group> group_list = user_formUtil.getGroupByUserId(user_id);
		
		ArrayList<Integer> group_id_list = user_formUtil.getGroupIdByUserId(user_id);
		
		user_formUtil.updateEventScheduleImageFaceByGroupIdList(group_id_list);
				
		model.addAttribute("group_list", group_list);
		
		user_formUtil.updateAlbumByUserId(user_id);
		
		ArrayList<Integer> event_id_list = eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		user_formUtil.updateAlbumByUserId(user_id);
		
		model.addAttribute("image_list", albumDAO.selectAlbumByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id));
		
		return "member/memberPhotoForm";
	}
	
}
