package project.ppaya.square.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

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

@Repository
@Controller
public class GroupAction {

	private static final Logger logger = LoggerFactory.getLogger(GroupAction.class);
	
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
	@ResponseBody
	@RequestMapping(value = "getGroupCommentTranslation", method = RequestMethod.POST)
	public String getTranslation(Model request, @RequestBody HashMap<String, Object> map)
	{
		int group_comment_id = (int)map.get("group_comment_id");	
		String language = (String)map.get("language");
		
		GroupComment group_comment = yh_group_commentDAO.selectGroupCommentByGroupCommentId(group_comment_id); 
		
		String result = YHGoogleTranslationUtil.getTranslation(group_comment.getContent(), "ja", language);
		
		JSONObject jsonObject = new JSONObject(); 
		
		try
		{
			jsonObject.put("result", URLEncoder.encode(result, "utf-8"));
		}
		catch(Exception error){error.printStackTrace();}
		
		return jsonObject.toString();
	}
	@ResponseBody
	@RequestMapping(value = "resetGroupComment", method = RequestMethod.POST)
	public String resetComment(Model request, @RequestBody HashMap<String, Object> map)
	{
		int group_comment_id = (int)map.get("group_comment_id");	
		
		GroupComment group_comment = yh_group_commentDAO.selectGroupCommentByGroupCommentId(group_comment_id);
		
		String result = group_comment.getContent();
		
		JSONObject jsonObject = new JSONObject(); 
		
		try
		{
			jsonObject.put("result", URLEncoder.encode(result, "utf-8"));
		}
		catch(Exception error){error.printStackTrace();}
		
		return jsonObject.toString();
	}
	@ResponseBody
	@RequestMapping(value = "createGroupAction", method = RequestMethod.POST)
	public void createGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		String user_id = "id1@gmail.com";
		String name = (String)map.get("name");
		String content = (String)map.get("content");
		int group_category_id = Integer.parseInt((String)map.get("group_category_id"));
		ArrayList<String> group_hashtag_list = ( ArrayList<String> )map.get("group_hashtag_list");
		int check  = sh_gdao.setGroupHashtag(group_hashtag_list);
		
		
		String region = (String)map.get("region");
		String group_logo = YHFileUtil.saveJpegFromBase64((String)map.get("group_logo"), Reference.group_logo_path);
		String group_image = YHFileUtil.saveJpegFromBase64((String)map.get("group_image"), Reference.group_image_path);
		
		while(true)
		{
			if(yh_groupDAO.insertGroup(group_category_id, user_id, name, content, region, group_logo, group_image) != 0)
			{
				break;
			}
		}
	}
}
