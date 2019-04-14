package project.ppaya.square.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.shdao.*;
import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class GroupAction {

	private static final Logger logger = LoggerFactory.getLogger(GroupAction.class);
	
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
	@RequestMapping(value = "listGroupAction", method = RequestMethod.POST)
	public ArrayList<Group> listGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		int group_category_id = (int)map.get("group_category_id"); 
		String keyword = (String)map.get("keyword");
		
		return yh_groupDAO.selectGroupByGroupCategoryIdName(group_category_id, keyword);
	}
	@ResponseBody
	@RequestMapping(value = "joinGroupAction", method = RequestMethod.POST)
	public void joinGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		int group_id = (int)map.get("group_id");		
		
		yh_group_attendanceDAO.insertGroupAttendance(user_id, group_id);
	}
	@ResponseBody
	@RequestMapping(value = "withdrawGroupAction", method = RequestMethod.POST)
	public void withdrawGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		int group_id = (int)map.get("group_id");		
		
		yh_group_attendanceDAO.deleteGroupAttendanceByGroupIdUserId(user_id, group_id);
	}
}
