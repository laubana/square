package project.ppaya.square.yhutil;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhthread.YHUpdateEventScheduleImageFaceThread;
import project.ppaya.square.yhthread.YHUpdateEventScheduleVideoFaceThread1;
import project.ppaya.square.yhthread.YHUpdateVideoAlbumThread;

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
	@Autowired
	YHVideoAppearanceDAO yh_video_appearanceDAO;
	
	public void updateVideoAlbum(String user_id, String path)
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
		
		int index = 0;
		YHUpdateVideoAlbumThread.out_map.put(index, new HashMap<>());
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			YHUpdateVideoAlbumThread thread = new YHUpdateVideoAlbumThread(index, i, event_schedule_video_id_list.get(i), user, path);
			thread.start();
			
			/*ArrayList<String> event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id_list.get(i));
			
			if(event_schedule_video_face_id_list.size() != 0)
			{
				ArrayList<String> similar_event_schedule_video_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(event_schedule_video_face_id_list, YHMSFaceUtil.getFaceId(Reference.user_image_path, user.getImage_id()));
				
				if(similar_event_schedule_video_face_id.size() != 0)
				{
					yh_video_albumDAO.updateSelfByEventScheduleVideoIdUserId(event_schedule_video_id_list.get(i), user_id);
				}
			}*/
		}
		out_while:
		while(true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(Exception error){error.printStackTrace();}
			for(int i = 0; i < event_schedule_video_id_list.size(); i++)
			{
				if((boolean)YHUpdateVideoAlbumThread.out_map.get(index).get(i) == false)
				{
					continue out_while;
				}
			}
			break;
		}
		System.err.println("done");
	}
	/*public void updateEventScheduleVideoFaceByGroupId(int group_id)
	{
		String result;
		JSONArray jsonArray;
		JSONObject jsonObject;
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByGroupId(group_id);
		
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		
		int index = 0;
		for(int i = 0; i < event_schedule_video_list.size(); i++)
		{			
			YHUpdateEventScheduleVideoFaceThread1 thread = new YHUpdateEventScheduleVideoFaceThread1(index, event_schedule_video_list.get(i));
			thread.start();
			if(event_schedule_video_list.get(i).getDetect_date() == null)
			{				
				result = YHMSVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(result);
				
				if(jsonObject.isNull("errorType"))
				{
					yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
					
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						JSONObject temp_jsonObject = jsonArray.getJSONObject(j);
						
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), temp_jsonObject.getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						String face_id = YHMSFaceUtil.getFaceId(Reference.event_schedule_video_face_path, event_schedule_video_image_id);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(face_id, event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
						
						JSONArray temp_jsonArray = temp_jsonObject.getJSONArray("appearances");
						
						for(int k = 0; k < temp_jsonArray.length(); k++)
						{
							temp_jsonObject = temp_jsonArray.getJSONObject(k);
							
							String string_start_time = temp_jsonObject.getString("startTime");							
							long start_time = 0;
							
							start_time += Long.parseLong(string_start_time.split(":")[0]) * 3600000;
							start_time += Long.parseLong(string_start_time.split(":")[1]) * 60000;
							start_time += Long.parseLong(string_start_time.split(":")[2].split(".")[0]) * 1000;
							start_time += Long.parseLong(string_start_time.split(":")[2].split(".")[1]);
							
							String string_end_time = temp_jsonObject.getString("endTime");							
							long end_time = 0;
							
							end_time += Long.parseLong(string_end_time.split(":")[0]) * 3600000;
							end_time += Long.parseLong(string_end_time.split(":")[1]) * 60000;
							end_time += Long.parseLong(string_end_time.split(":")[2].split(".")[0]) * 1000;
							end_time += Long.parseLong(string_end_time.split(":")[2].split(".")[1]);
							
							yh_video_appearanceDAO.insertVideoAppearance(face_id, start_time, end_time);
						}
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video_list.get(i).getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(YHMSVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id()));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(YHMSFaceUtil.getFaceId(Reference.event_schedule_video_face_path, event_schedule_video_image_id), event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
					}
				}		
			}
		}
		
		while(true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(Exception error){}
			if(YHUpdateEventScheduleVideoFaceThread1.map == event_schedule_video_list.size())
			{
				break;
			}
		}
		System.err.println("done");
	}*/

	public void updateEventScheduleVideoFace(String user_id, String path)
	{
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<EventScheduleVideo> event_schedule_video_list = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		
		int index = 0;
		YHUpdateEventScheduleVideoFaceThread1.out_map.put(index, new HashMap<>());
		for(int i = 0; i < event_schedule_video_list.size(); i++)
		{			
			YHUpdateEventScheduleVideoFaceThread1 thread = new YHUpdateEventScheduleVideoFaceThread1(index, i, event_schedule_video_list.get(i), path);
			thread.start();
			/*if(event_schedule_video_list.get(i).getDetect_date() == null)
			{				
				result = YHMSVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(result);
				
				if(jsonObject.isNull("errorType"))
				{
					yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
					
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						JSONObject temp_jsonObject = jsonArray.getJSONObject(j);
						
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), temp_jsonObject.getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						String face_id = YHMSFaceUtil.getFaceId(Reference.event_schedule_video_face_path, event_schedule_video_image_id);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(face_id, event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
						
						JSONArray temp_jsonArray = temp_jsonObject.getJSONArray("appearances");
						
						for(int k = 0; k < temp_jsonArray.length(); k++)
						{
							temp_jsonObject = temp_jsonArray.getJSONObject(k);
							
							String string_start_time = temp_jsonObject.getString("startTime");							
							long start_time = 0;
							
							start_time += Long.parseLong(string_start_time.split(":")[0]) * 3600000;
							start_time += Long.parseLong(string_start_time.split(":")[1]) * 60000;
							if(string_start_time.contains("."))
							{
								start_time += Long.parseLong(string_start_time.split(":")[2].split("\\.")[0]) * 1000;
								start_time += Long.parseLong(string_start_time.split(":")[2].split("\\.")[1]);
							}
							else
							{
								start_time += Long.parseLong(string_start_time.split(":")[2]) * 1000;
							}
							
							String string_end_time = temp_jsonObject.getString("endTime");							
							long end_time = 0;
							
							end_time += Long.parseLong(string_end_time.split(":")[0]) * 3600000;
							end_time += Long.parseLong(string_end_time.split(":")[1]) * 60000;
							if(string_end_time.contains("."))
							{
								end_time += Long.parseLong(string_end_time.split(":")[2].split("\\.")[0]) * 1000;
								end_time += Long.parseLong(string_end_time.split(":")[2].split("\\.")[1]);
							}
							else
							{
								end_time += Long.parseLong(string_end_time.split(":")[2]) * 1000;
							}
							
							yh_video_appearanceDAO.insertVideoAppearance(face_id, start_time, end_time);
						}
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video_list.get(i).getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_list.get(i).getEvent_schedule_video_id());
				
				jsonObject = new JSONObject(YHMSVideoIndexerUtil.getVideoIndex(event_schedule_video_list.get(i).getEvent_schedule_video_id()));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video_list.get(i).getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.event_schedule_video_face_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(YHMSFaceUtil.getFaceId(Reference.event_schedule_video_face_path, event_schedule_video_image_id), event_schedule_video_image_id, event_schedule_video_list.get(i).getEvent_schedule_video_id());
					}
				}		
			}*/
		}
		out_while:
		while(true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(Exception error){}
			for(int i = 0; i < event_schedule_video_list.size(); i++)
			{
				if((boolean)YHUpdateEventScheduleVideoFaceThread1.out_map.get(index).get(i) == false)
				{
					continue out_while;
				}
			}
			break;
		}
		System.err.println("done");
	}
	public void updateEventScheduleImageFace(String user_id, String path)
	{
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		int index = 0;
		YHUpdateEventScheduleImageFaceThread.out_map.put(index, new HashMap<>());
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			YHUpdateEventScheduleImageFaceThread thread = new YHUpdateEventScheduleImageFaceThread(index, i, event_schedule_image_list.get(i), path);
			thread.start();
			/*if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = YHMSFaceUtil.getFace(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
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
				
				result = YHMSFaceUtil.getFace(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
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
			}*/
		}
		out_while:
		while(true)
		{
			try
			{
				Thread.sleep(100);
			}
			catch(Exception error){}
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				if((boolean)YHUpdateEventScheduleImageFaceThread.out_map.get(index).get(i) == false)
				{
					continue out_while;
				}
			}
			break;
		}
		System.err.println("done");
	}
	public void updateImageAlbum(String user_id, String path)
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
		
		if(event_schedule_image_face_id_list.size() != 0)
		{
			ArrayList<String> similar_event_schedule_image_face_id_list = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(event_schedule_image_face_id_list, YHMSFaceUtil.getFaceId(path + Reference.user_image_path, user.getImage_id()));
			
			ArrayList<String> similar_event_schedule_image_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id_list);
			
			yh_image_albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
		}
	}
}
