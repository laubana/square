package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import project.ppaya.square.shdao.*;
import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class UserController
{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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
	
	@RequestMapping(value = "listUserGroupForm", method = RequestMethod.GET)
	public String listUserGroupForm
	(
			Model request,
			@RequestParam(value = "user_id", defaultValue = "id1@gmail.com") String user_id
			)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		//User 전송
		request.addAttribute("user", user);
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserId(user_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);
		for(int i = 0; i < group_list.size(); i++)
		{
			group_list.get(i).setBlind(yh_group_attendanceDAO.getBlindByUserIdGroupId(user_id, group_list.get(i).getGroup_id()));
		}
		
		//Group List 전송
		request.addAttribute("group_list", group_list);
		
		return "user/listUserGroupForm";
	}
	@RequestMapping(value = "joinUserForm", method = RequestMethod.GET)
	public String joinUserFormGET()
	{
		return "user/joinUserForm";
	}
	@RequestMapping(value = "loginUserForm", method = RequestMethod.GET)
	public String loginUserFormGET()
	{
		return "user/loginUserForm";
	}
	@RequestMapping(value = "viewUserForm", method = RequestMethod.GET)
	public String viewUserForm
	(
			Model request,
			@RequestParam(value = "user_id", defaultValue = "id1@gmail.com") String user_id
			//String user_id
			)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		//User 전송
		request.addAttribute("user", user);
		
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserIdNotBlind(user_id);
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);
		//Group List 전송
		request.addAttribute("group_list", group_list);
		
		ArrayList<UserHashtag> user_hashtag_list = yhuser_hashtagDAO.selectUserHashtagByUserId(user_id);
		//UserHashtag List 전송
		request.addAttribute("user_hashtag_list", user_hashtag_list);
		
		ArrayList<String> event_schedule_image_id_list = yh_image_albumDAO.getEventScheduleImageIdByUserIdNotBlind(user_id);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleImageIdList(event_schedule_image_id_list);
		//EventScheduleImage List 전송
		request.addAttribute("event_schedule_image_list", event_schedule_image_list);
		
		ArrayList<String> event_schedule_video_id_list = yh_video_albumDAO.getEventScheduleVideoIdByUserIdNotBlind(user_id);
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
		//EventScheduleVideo List 전송
		request.addAttribute("video_list", event_schedule_video_list);
		
		return "user/viewUserForm";
	}
	@RequestMapping(value = "listUserAlbumForm", method = RequestMethod.GET)
	public String listUserAlbumForm
	(
			Model request,
			@RequestParam(value = "user_id", defaultValue = "id1@gmail.com") String user_id
			//String user_id
			)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;	
		
		User user = yh_userDAO.selectUserByUserId(user_id);
		//User 전송
		request.addAttribute("user", user);
		String image_id = user.getImage_id();
		
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserId(user_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);		
		request.addAttribute("group_list", group_list);
		
		//EventScheduleImageFace 업데이트
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = YHMSFaceUtil.detectFace(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					yh_event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_list.get(i).getEvent_schedule_image_id(),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				yh_event_schedule_image_faceDAO.deleteEventScheduleImageFaceByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id());
				
				result = YHMSFaceUtil.detectFace(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					yh_event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_list.get(i).getEvent_schedule_image_id(),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
		//EventScheduleImageFace 업데이트 끝

		//ImageAlbum 업데이트
		ArrayList<Integer> attend_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(attend_event_schedule_id_list);

		yh_image_albumDAO.deleteImageAlbumByNotEventScheduleImageIdUserId(attend_event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_image_id_list.size(); i++)
		{
			yh_image_albumDAO.insertImageAlbum(attend_event_schedule_image_id_list.get(i), user_id);
		}
		
		yh_image_albumDAO.updateSelfByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_image_face_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(attend_event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_image_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.user_image_path, image_id))).getJSONObject(0).getString("faceId"));
		
		ArrayList<String> similar_event_schedule_image_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id);
		
		yh_image_albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
		//ImageAlbum 업데이트 끝
		
		//EventScheduleVideoFace 업데이트
		event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			String event_schedule_video_id = event_schedule_video_id_list.get(i);
			
			EventScheduleVideo event_schedule_video = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoId(event_schedule_video_id);
			
			if(event_schedule_video.getDetect_date() == null)
			{				
				jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_id);
					
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_video_face_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video.getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_id);
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_id);
				
				jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_video_face_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
				}		
			}
		}
		//EventScheduleVideoFace 업데이트 끝
		
		//VideoAlbum 업데이트
		attend_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(attend_event_schedule_id_list);
		
		yh_video_albumDAO.deleteVideoAlbumByNotEventScheduleVideoIdUserId(attend_event_schedule_video_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_video_id_list.size(); i++)
		{
			yh_video_albumDAO.insertVideoAlbum(attend_event_schedule_video_id_list.get(i), user_id);
		}
		
		yh_video_albumDAO.updateSelfByUserId(user_id);
		
		for(int i = 0; i < attend_event_schedule_video_id_list.size(); i++)
		{
			ArrayList<String> attend_event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(attend_event_schedule_video_id_list.get(i));
			
			if(attend_event_schedule_video_face_id_list.size() != 0)
			{
				ArrayList<String> similar_event_schedule_video_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_video_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.user_image_path, image_id))).getJSONObject(0).getString("faceId"));
				
				if(similar_event_schedule_video_face_id.size() != 0)
				{
					yh_video_albumDAO.updateSelfByEventScheduleVideoIdUserId(attend_event_schedule_video_id_list.get(i), user_id);
				}
			}
		}
		//VideoAlbum 업데이트 끝
		
		return "user/listUserAlbumForm";
	}
}
