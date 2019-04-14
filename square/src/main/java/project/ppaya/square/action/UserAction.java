package project.ppaya.square.action;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.shdao.*;
import project.ppaya.square.vo.*;
import project.ppaya.square.vo.UserHashtag;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class UserAction {

	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleAttendanceDAO yh_event_schedule_attendanceDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHEventScheduleImageFaceDAO yh_event_schedule_image_faceDAO;
	@Autowired
	YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHUserHashtagDAO yhuser_hashtagDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO; 
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@ResponseBody
	@RequestMapping(value = "verifyUserIdAction", method = RequestMethod.POST)
	public String verifyUserIdAction(String user_id)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		
		if(user != null)
		{
			return "false";
		}
		else
		{
			return "true";
		}
	}
	@ResponseBody
	@RequestMapping(value = "loginUserFormAction", method = RequestMethod.POST)
	public String loginUserFormAction(HttpSession session, String user_id, String password)
	{
		User user = yh_userDAO.selectUserByUserIdPassword(user_id, password);
		
		if(user != null)
		{
			session.setAttribute("user_id", user_id);
			return "true";
		}
		else
		{
			return "false";
		}
	}
	@ResponseBody
	@RequestMapping(value = "logoutUserAction", method = RequestMethod.POST)
	public void logoutUserAction(HttpSession session)
	{
		session.removeAttribute("user_id");
	}
}
