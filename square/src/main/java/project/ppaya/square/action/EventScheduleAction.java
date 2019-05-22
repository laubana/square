package project.ppaya.square.action;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
public class EventScheduleAction {

	private static final Logger logger = LoggerFactory.getLogger(EventScheduleAction.class);
	
	@Autowired
	YHEventScheduleCommentDAO yh_event_schedule_commentDAO;
	@Autowired
	YHEventScheduleUserScheduleDAO yh_event_schedule_user_scheduleDAO;
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleCommentTagDAO yh_event_schedule_comment_tagDAO;
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
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@ResponseBody
	@RequestMapping(value = "writeEventScheduleCommentAction", method = RequestMethod.POST)
	public String writeEventScheduleCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_schedule_id = (int)map.get("event_schedule_id");
		String content = (String)map.get("content");
		
		while(true)
		{
			if(yh_event_schedule_commentDAO.insertEventScheduleComment(event_schedule_id, user_id, content) != 0)
			{
				break;
			}
		}
		
		ArrayList<EventScheduleComment> event_schedule_comment_list = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleId(event_schedule_id);		
		for(int i = 0; i < event_schedule_comment_list.size(); i++)
		{
			ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(event_schedule_comment_list.get(i).getContent(), "en");
			
			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_event_schedule_comment_tagDAO.insertEventScheduleCommentTag(event_schedule_comment_list.get(i).getEvent_schedule_comment_id(), source_tag_list.get(j));
			}
		}
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "deleteEventScheduleCommentAction", method = RequestMethod.POST)
	public String deleteGroupCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_schedule_comment_id = (int)map.get("event_schedule_comment_id");
		
		yh_event_schedule_commentDAO.deleteEventScheduleCommentByEventScheduleCommentIdUserId(event_schedule_comment_id, user_id);
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "updateEventScheduleCommentAction", method = RequestMethod.POST)
	public String updateGroupCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_schedule_comment_id = (int)map.get("event_schedule_comment_id");
		String content = (String)map.get("content");
		
		yh_event_schedule_commentDAO.updateContentByEventScheduleCommentIdUserId(event_schedule_comment_id, user_id, content);
		
		yh_event_schedule_comment_tagDAO.deleteEventScheduleCommentByEventScheduleCommentId(event_schedule_comment_id);
		
		ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(content, "en");
			
		ArrayList<String> target_tag_list = new ArrayList<>();
		for(int j = 0; j < source_tag_list.size(); j++)
		{
			target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
		}
		
		for(int j = 0; j < source_tag_list.size(); j++)
		{
			yh_event_schedule_comment_tagDAO.insertEventScheduleCommentTag(event_schedule_comment_id, source_tag_list.get(j));
		}
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "insertEventScheduleImageAction", method = RequestMethod.POST)
	public String insertEventScheduleImageAction(Model request, HttpSession session, HttpServletRequest servletRequest, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int group_category_id = (int)map.get("group_category_id");
		int group_id = (int)map.get("group_id");
		int event_id = (int)map.get("event_id");
		int event_schedule_id = (int)map.get("event_schedule_id");
		String filename = (String)map.get("filename");
		String image = (String)map.get("image");
		String ext = YHFileUtil.getExt(filename);
		String event_schedule_image_id = YHFileUtil.saveJpegFromBase64(image, servletRequest.getSession().getServletContext().getRealPath("") + Reference.event_schedule_image_path);
		
		yh_event_schedule_imageDAO.insertEventScheduleImage(event_schedule_image_id, user_id, group_category_id, group_id, event_id, event_schedule_id, filename, ext);
		
		ArrayList<String> source_description_list = YHMSComputerVisionUtil.getDescriptionList(servletRequest.getSession().getServletContext().getRealPath("") + Reference.event_schedule_image_path, event_schedule_image_id, "ja");
			
		for(int j = 0; j < source_description_list.size(); j++)
		{
			yh_event_schedule_imageDAO.updateDescriptionByEventScheduleImageId(source_description_list.get(j), event_schedule_image_id);
		}
		
		return "success";
	}
	@ResponseBody
	@RequestMapping(value = "createEventScheduleAction", method = RequestMethod.POST)
	public void createEventScheduleAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String name = (String)map.get("name");
		String content = (String)map.get("content");
		int group_id = (int)map.get("group_id");
		int event_id = (int)map.get("event_id");
		String region = (String)map.get("region");
		String address = (String)map.get("address");
		String latitude = (String)map.get("latitude");
		String longitude = (String)map.get("longitude");
		long start_date = -1;
		long end_date = -1;
		try
		{
			start_date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse((String)map.get("start_date")).getTime();
			end_date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).parse((String)map.get("end_date")).getTime();
		}
		catch(Exception error){}		
		
		while(true)
		{
			if(yh_event_scheduleDAO.insertEventSchedule(group_id, event_id, name, content, region, address, latitude, longitude, start_date, end_date) != 0)
			{
				break;
			}
		}
	}
	@ResponseBody
	@RequestMapping(value = "getEventScheduleCommentTranslation", method = RequestMethod.POST)
	public String getEventScheduleCommentTranslation(Model request, @RequestBody HashMap<String, Object> map)
	{
		int event_schedule_comment_id = (int)map.get("event_schedule_comment_id");	
		String language = (String)map.get("language");
		
		EventScheduleComment event_schedule_comment = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleCommentId(event_schedule_comment_id); 
		
		String result = YHGoogleTranslationUtil.getTranslation(event_schedule_comment.getContent(), "ja", language);
		
		JSONObject jsonObject = new JSONObject(); 
		
		try
		{
			jsonObject.put("result", URLEncoder.encode(result, "utf-8"));
		}
		catch(Exception error){error.printStackTrace();}
		
		return jsonObject.toString();
	}
	@ResponseBody
	@RequestMapping(value = "resetEventScheduleComment", method = RequestMethod.POST)
	public String resetEventScheduleComment(Model request, @RequestBody HashMap<String, Object> map)
	{
		int event_schedule_comment_id = (int)map.get("event_schedule_comment_id");	
		
		EventScheduleComment event_schedule_comment = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleCommentId(event_schedule_comment_id);
		
		String result = event_schedule_comment.getContent();
		
		JSONObject jsonObject = new JSONObject(); 
		
		try
		{
			jsonObject.put("result", URLEncoder.encode(result, "utf-8"));
		}
		catch(Exception error){error.printStackTrace();}
		
		return jsonObject.toString();
	}
	@ResponseBody
	@RequestMapping(value = "joinEventScheduleAction", method = RequestMethod.POST)
	public void joinEventScheduleAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_schedule_id = (int)map.get("event_schedule_id");
		ArrayList<HashMap<String, Long>> schedule_list = (ArrayList<HashMap<String, Long>>)map.get("schedule_list");
		
		yh_event_schedule_user_scheduleDAO.deleteEventScheduleUserScheduleByUserIdEventScheduleId(user_id, event_schedule_id);
		
		for(int i = 0; i < schedule_list.size(); i++)
		{
			try
			{
				yh_event_schedule_user_scheduleDAO.insertEventScheduleUserSchedule(
								user_id,
								event_schedule_id,
								schedule_list.get(i).get("start_date"),
								schedule_list.get(i).get("end_date")
						);
			}
			catch(Exception error){error.printStackTrace();}
		}
		
		yh_event_schedule_attendanceDAO.insertEventScheduleAttendance(user_id, event_schedule_id);
	}
	@ResponseBody
	@RequestMapping(value = "withdrawEventScheduleAction", method = RequestMethod.POST)
	public void withdrawEventScheduleAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int event_schedule_id = (int)map.get("event_schedule_id");	

		yh_event_schedule_user_scheduleDAO.deleteEventScheduleUserScheduleByUserIdEventScheduleId(user_id, event_schedule_id);
		
		yh_event_schedule_attendanceDAO.deleteEventScheduleAttendanceByEventScheduleIdUserId(user_id, event_schedule_id);
	}
}
