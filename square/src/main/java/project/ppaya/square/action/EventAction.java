package project.ppaya.square.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class EventAction {

	private static final Logger logger = LoggerFactory.getLogger(EventAction.class);
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventCommentDAO yh_event_commentDAO;
	@Autowired
	YHEventCommentTagDAO yh_event_comment_tagDAO;
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
	@RequestMapping(value = "deleteEventCommentAction", method = RequestMethod.POST)
	public String deleteGroupCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_comment_id = (int)map.get("event_comment_id");
		
		yh_event_commentDAO.deleteEventCommentByEventCommentIdUserId(event_comment_id, user_id);
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "updateEventCommentAction", method = RequestMethod.POST)
	public String updateEventCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_comment_id = (int)map.get("event_comment_id");
		String content = (String)map.get("content");
		
		yh_event_commentDAO.updateContentByEventCommentIdUserId(event_comment_id, user_id, content);
		
		yh_event_comment_tagDAO.deleteEventCommentByEventCommentId(event_comment_id);
		
		ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(content, "en");
			
		ArrayList<String> target_tag_list = new ArrayList<>();
		for(int j = 0; j < source_tag_list.size(); j++)
		{
			target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
		}
		
		for(int j = 0; j < source_tag_list.size(); j++)
		{
			yh_event_comment_tagDAO.insertEventCommentTag(event_comment_id, source_tag_list.get(j));
		}
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "createEventAction", method = RequestMethod.POST)
	public void createEventAction(Model request, HttpSession session, HttpServletRequest servletReqeust, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		String name = (String)map.get("name");
		String content = (String)map.get("content");
		int group_category_id = (int)map.get("group_category_id");
		int group_id = (int)map.get("group_id");
		String image_id = YHFileUtil.saveJpegFromBase64((String)map.get("event_image"), servletReqeust.getSession().getServletContext().getRealPath("") + Reference.event_image_path);
		
		while(true)
		{
			if(yh_eventDAO.insertEvent(name, content, user_id, group_category_id, group_id, image_id) != 0)
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
		int event_id = (int)map.get("event_id");
		String name = (String)map.get("keyword");
		
		ArrayList<Integer> group_id_list = yh_event_unionDAO.getGroupIdByEventId(event_id);
		group_id_list.add(group_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByNameNotGroupIdList(name, group_id_list);
		
		return group_list;
	}
	@ResponseBody
	@RequestMapping(value = "writeEventCommentAction", method = RequestMethod.POST)
	public String writeEventCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_id = (int)map.get("event_id");
		String content = (String)map.get("content");
		
		while(true)
		{
			if(yh_event_commentDAO.insertEventComment(event_id, user_id, content) != 0)
			{
				break;
			}
		}
		
		ArrayList<EventComment> event_comment_list = yh_event_commentDAO.selectEventCommentByEventId(event_id);		
		for(int i = 0; i < event_comment_list.size(); i++)
		{
			ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(event_comment_list.get(i).getContent(), "en");
			
			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_event_comment_tagDAO.insertEventCommentTag(event_comment_list.get(i).getEvent_comment_id(), source_tag_list.get(j));
			}
		}
		
		return "success";
	}
}
