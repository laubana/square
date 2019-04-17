package project.ppaya.square.yhutil;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;

@Repository
public class YHUtil
{
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
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	@Autowired
	YHUserDAO yh_userDAO;
	
	public void updateVideoAlbum(String user_id)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		yh_video_albumDAO.deleteVideoAlbumByNotEventScheduleVideoIdUserId(event_schedule_video_id_list, user_id);
		
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			yh_video_albumDAO.insertVideoAlbum(event_schedule_video_id_list.get(i), user_id);
		}
		
		yh_video_albumDAO.updateSelfByUserId(user_id);
		
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			ArrayList<String> event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id_list.get(i));
			
			if(event_schedule_video_face_id_list.size() != 0)
			{
				ArrayList<String> similar_event_schedule_video_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(event_schedule_video_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.user_image_path, user.getImage_id()))).getJSONObject(0).getString("faceId"));
				
				if(similar_event_schedule_video_face_id.size() != 0)
				{
					yh_video_albumDAO.updateSelfByEventScheduleVideoIdUserId(event_schedule_video_id_list.get(i), user_id);
				}
			}
		}
	}
	public void updateEventScheduleVideoFaceByGroupId(int group_id)
	{
		String result;
		JSONArray jsonArray;
		JSONObject jsonObject;
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(group_id);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_video_list.size(); i++)
		{			
			if(event_schedule_video_list.get(i).getDetect_date() == null)
			{				
				result = YHVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(result);
				
				if(jsonObject.isNull("errorType"))
				{
					yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
					
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_video_face_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video_list.get(i).getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id()));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_video_face_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
					}
				}		
			}
		}
	}

	public void updateEventScheduleVideoFace(String user_id)
	{
		String result;
		JSONArray jsonArray;
		JSONObject jsonObject;
		
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_video_list.size(); i++)
		{			
			if(event_schedule_video_list.get(i).getDetect_date() == null)
			{				
				result = YHVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(result);
				
				if(jsonObject.isNull("errorType"))
				{
					yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
					
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_video_face_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video_list.get(i).getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id()));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.event_schedule_video_face_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
					}
				}		
			}
		}
	}
	public void updateEventScheduleImageFace(String user_id)
	{
		String result;
		JSONArray jsonArray;
		JSONObject jsonObject;
		
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
	}
	public void updateImageAlbum(String user_id)
	{
		User user = yh_userDAO.selectUserByUserId(user_id);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);

		yh_image_albumDAO.deleteImageAlbumByNotEventScheduleImageIdUserId(event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < event_schedule_image_id_list.size(); i++)
		{
			yh_image_albumDAO.insertImageAlbum(event_schedule_image_id_list.get(i), user_id);
		}
		
		yh_image_albumDAO.updateSelfByUserId(user_id);
		
		ArrayList<String> event_schedule_image_face_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id_list = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(event_schedule_image_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.user_image_path, user.getImage_id()))).getJSONObject(0).getString("faceId"));
		
		ArrayList<String> similar_event_schedule_image_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id_list);
		
		yh_image_albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
	}
}
