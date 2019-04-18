package project.ppaya.square.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

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

@Repository
@Controller
public class EventAction {

	private static final Logger logger = LoggerFactory.getLogger(EventAction.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventCommentDAO yh_event_commentDAO;
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
	YHEventAttendanceDAO yh_event_attendanceDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO; 
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	@Autowired
	YHEventUnionDAO yh_event_unionDAO;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@ResponseBody
	@RequestMapping(value = "createEventAction", method = RequestMethod.POST)
	public void createEventAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		String name = (String)map.get("name");
		String content = (String)map.get("content");
		int group_id = (int)map.get("group_id");
		String image_id = YHFileUtil.saveJpegFromBase64((String)map.get("event_image"), Reference.event_image_path);		
		int reult;
		
		while(true)
		{
			if(yh_eventDAO.insertEvent(name, content, user_id, group_id, image_id) != 0)
			{
				break;
			}
		}
	}
	@ResponseBody
	@RequestMapping(value = "getEventCommentTranslation", method = RequestMethod.POST)
	public String getEventCommentTranslation(Model request, @RequestBody HashMap<String, Object> map)
	{
		int event_comment_id = (int)map.get("event_comment_id");	
		String language = (String)map.get("language");
		
		EventComment event_comment = yh_event_commentDAO.selectEventCommentByEventCommentId(event_comment_id); 
		
		String result = YHGoogleTranslationUtil.getTranslation(event_comment.getContent(), "ja", language);
		
		JSONObject jsonObject = new JSONObject(); 
		
		try
		{
			jsonObject.put("result", URLEncoder.encode(result, "utf-8"));
		}
		catch(Exception error){error.printStackTrace();}
		
		return jsonObject.toString();
	}
	@ResponseBody
	@RequestMapping(value = "resetEventComment", method = RequestMethod.POST)
	public String resetEventComment(Model request, @RequestBody HashMap<String, Object> map)
	{
		int event_comment_id = (int)map.get("event_comment_id");	
		
		EventComment event_comment = yh_event_commentDAO.selectEventCommentByEventCommentId(event_comment_id);
		
		String result = event_comment.getContent();
		
		JSONObject jsonObject = new JSONObject(); 
		
		try
		{
			jsonObject.put("result", URLEncoder.encode(result, "utf-8"));
		}
		catch(Exception error){error.printStackTrace();}
		
		return jsonObject.toString();
	}
	@ResponseBody
	@RequestMapping(value = "joinEventAction", method = RequestMethod.POST)
	public void joinEventAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		int event_id = (int)map.get("event_id");		
		
		yh_event_attendanceDAO.insertEventAttendance(user_id, event_id);
	}
	@ResponseBody
	@RequestMapping(value = "withdrawEventAction", method = RequestMethod.POST)
	public void withdrawEventAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		int event_id = (int)map.get("event_id");	
		
		yh_event_attendanceDAO.deleteEventAttendanceByEventIdUserId(user_id, event_id);
	}
	@ResponseBody
	@RequestMapping(value = "unifyEventAction", method = RequestMethod.POST)
	public void unifyEventAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		int event_id = (int)map.get("event_id");
		int current_group_id = (int)map.get("current_group_id");
		int group_id = (int)map.get("group_id");
		
		yh_event_unionDAO.insertEventUnion(event_id, group_id);
		yh_event_unionDAO.insertEventUnion(event_id, current_group_id);
	}
	@ResponseBody
	@RequestMapping(value = "searchGroupAction", method = RequestMethod.POST)
	public ArrayList<Group> searchGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		int group_id = (int)map.get("group_id");
		String name = (String)map.get("keyword");
		
		ArrayList<Integer> group_id_list = yh_groupDAO.getGroupIdByName(name);
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdListNotGroupId(group_id_list, group_id);
		
		return group_list;
	}
}
