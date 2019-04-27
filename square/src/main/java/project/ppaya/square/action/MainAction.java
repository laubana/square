package project.ppaya.square.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
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

@Controller
public class MainAction
{

	private static final Logger logger = LoggerFactory.getLogger(MainAction.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHGroupCommentDAO yh_group_commentDAO;
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
	YHGroupCommentTagDAO yh_group_comment_tagDAO;
	@Autowired
	YHCommentTagDAO yh_comment_tagDAO;
	@Autowired
	YHKeywordHistoryDAO yh_keyword_historyDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@ResponseBody
	@RequestMapping(value = "setGroupAction", method = RequestMethod.POST)
	public HashMap<String, Object> setGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		HashMap<String, Object> result = new HashMap<>();
		int group_id = (int)map.get("group_id");
		
		Group group = yh_groupDAO.selectGroupByGroupId(group_id);
		result.put("group", group);
		
		ArrayList<GroupHashtag> group_hashtag_list = yh_group_hashtagDAO.selectGroupHashtagByGroupId(group_id);
		result.put("group_hashtag_list", group_hashtag_list);
		
		ArrayList<EventSchedule> event_schedule_list = yh_event_scheduleDAO.selectEventScheduleByGroupId(group_id);
		result.put("event_schedule_list", event_schedule_list);
		
		return result;
	}
}
