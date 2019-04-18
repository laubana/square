package project.ppaya.square.action;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
	@RequestMapping(value = "createEventScheduleAction", method = RequestMethod.POST)
	public void createEventScheduleAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String name = (String)map.get("name");
		String content = (String)map.get("content");
		int group_id = (int)map.get("group_id");
		int event_id = (int)map.get("event_id");
		String region = (String)map.get("region");
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
			if(yh_event_scheduleDAO.insertEventSchedule(group_id, event_id, name, content, region, latitude, longitude, start_date, end_date) != 0)
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
	public void joinEventScheduleAction(Model request, @RequestBody HashMap<String, Object> test_map)
	{
		String user_id = (String)test_map.get("user_id");
		int event_schedule_id = (int)test_map.get("event_schedule_id");
		ArrayList<HashMap<String, String>> google_user_schedule_list = (ArrayList<HashMap<String, String>>)test_map.get("google_user_schedule_list");
		
		System.err.println(user_id + ", " + event_schedule_id);
		
		yh_event_schedule_user_scheduleDAO.deleteEventScheduleUserScheduleByUserIdEventScheduleId(user_id, event_schedule_id);
		
		for(int i = 0; i < google_user_schedule_list.size(); i++)
		{
			
			try
			{
				yh_event_schedule_user_scheduleDAO.insertEventScheduleUserSchedule(
						new EventScheduleUserSchedule(
								user_id,
								event_schedule_id,
								new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(google_user_schedule_list.get(i).get("start_date")).getTime(),
								new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(google_user_schedule_list.get(i).get("end_date")).getTime(),
								1
								)
						);
			}
			catch(Exception error){error.printStackTrace();}
			/**/
		}
		
		yh_event_schedule_attendanceDAO.insertEventScheduleAttendance(user_id, event_schedule_id);
	}
	@ResponseBody
	@RequestMapping(value = "withdrawEventScheduleAction", method = RequestMethod.POST)
	public void withdrawEventScheduleAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)map.get("user_id");
		int event_schedule_id = (int)map.get("event_schedule_id");	
		
		yh_event_schedule_attendanceDAO.deleteEventScheduleAttendanceByEventScheduleIdUserId(user_id, event_schedule_id);
	}
}
