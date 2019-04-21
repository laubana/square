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
	@RequestMapping(value = "listGroupAction", method = RequestMethod.POST)
	public ArrayList<Group> listGroupAction(Model request, @RequestBody HashMap<String, Object> map)
	{
		int group_category_id = (int)map.get("group_category_id"); 
		String name = (String)map.get("name");
		int where = (int)map.get("where");
		int orderby = (int)map.get("orderby");
		String keyword = (String)map.get("keyword");
		
		if(where == 2 && keyword.length() != 0)
		{
			yh_keyword_historyDAO.insertKeywordHistory(keyword);
		}
		
		if(where == 1)
		{
			switch(orderby)
			{
			case 1:
				return yh_groupDAO.selectGroupByGroupCategoryIdNameOrderByGroupAttendanceCount(group_category_id, name);
			case 2:
				return yh_groupDAO.selectGroupByGroupCategoryIdNameOrderByEventCount(group_category_id, name);
			case 3:
				return yh_groupDAO.selectGroupByGroupCategoryIdNameOrderByEventScheduleCount(group_category_id, name);
			case 4:
				return yh_groupDAO.selectGroupByGroupCategoryIdNameOrderByInputdate(group_category_id, name);
			}
		}
		else
		{
			ArrayList<Integer> group_id_list = yh_group_hashtagDAO.getGroupIdByHashtagGroupCategoryId(name, group_category_id);
			switch(orderby)
			{
			case 1:
				return yh_groupDAO.selectGroupByGroupIdListOrderByInputdate(group_id_list);
			case 2:
				return yh_groupDAO.selectGroupByGroupIdListOrderByEventCount(group_id_list);
			case 3:
				return yh_groupDAO.selectGroupByGroupIdListOrderByEventScheduleCount(group_id_list);
			case 4:
				return yh_groupDAO.selectGroupByGroupIdListOrderByInputdate(group_id_list);
			}
		}
		
		return null;
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
	public void createGroupAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		String name = (String)map.get("name");
		String content = (String)map.get("content");
		int group_category_id = Integer.parseInt((String)map.get("group_category_id"));
		ArrayList<String> group_hashtag_list = (ArrayList<String>)map.get("group_hashtag_list");
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
		
		Group group = yh_groupDAO.selectGroupByExactName(name);
		
		for(int i = 0; i < group_hashtag_list.size(); i++)
		{
			yh_group_hashtagDAO.insertGroupHashtag(group_hashtag_list.get(i), group_category_id, group.getGroup_id());
		}
	}
	@ResponseBody
	@RequestMapping(value = "writeGroupCommentAction", method = RequestMethod.POST)
	public void writeGroupCommentAction(Model request, HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String user_id = (String)session.getAttribute("user_id");
		int group_id = (int)map.get("group_id");
		String content = (String)map.get("content");
		
		while(true)
		{
			if(yh_group_commentDAO.insertGroupComment(group_id, user_id, content) != 0)
			{
				break;
			}
		}
		
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(group_id);		
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(group_comment_list.get(i).getContent(), "en");
			
			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_group_comment_tagDAO.insertGroupCommentTag(group_comment_list.get(i).getGroup_comment_id(), source_tag_list.get(j));
				yh_comment_tagDAO.insertCommentTag(group_comment_list.get(i).getUser_id(), source_tag_list.get(j));
			}
		}
	}
}
