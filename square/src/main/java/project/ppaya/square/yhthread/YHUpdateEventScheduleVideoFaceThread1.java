package project.ppaya.square.yhthread;

import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHUpdateEventScheduleVideoFaceThread1 extends Thread
{	
	public static HashMap<Integer, HashMap<Integer, Object>> out_map = new HashMap<>();
	
	private YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	private YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	
	private EventScheduleVideo event_schedule_video;
	private int i;
	private int index;
	private String path;
	
	public YHUpdateEventScheduleVideoFaceThread1(int index, int i, EventScheduleVideo event_schedule_video, String path)
	{
		yh_event_schedule_videoDAO = (YHEventScheduleVideoDAO)YHBeanUtil.getBean("YHEventScheduleVideoDAO");
		yh_event_schedule_video_faceDAO = (YHEventScheduleVideoFaceDAO)YHBeanUtil.getBean("YHEventScheduleVideoFaceDAO");
		
		this.event_schedule_video = event_schedule_video;
		this.index = index;
		this.i = i;
		this.path = path;
		out_map.get(index).put(i, false);
	}
	@Override
	public void run()
	{
		String result;
		JSONArray jsonArray;
		JSONObject jsonObject;
		
		if(event_schedule_video.getDetect_date() == null)
		{				
			result = YHMSVideoIndexerUtil.getVideoIndex(event_schedule_video.getEvent_schedule_video_id());
			
			jsonObject = new JSONObject(result);
			
			if(jsonObject.isNull("errorType"))
			{
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video.getEvent_schedule_video_id());
				
				jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
				
				YHUpdateEventScheduleVideoFaceThread2.out_map.put(this.i, new HashMap<>());
				for(int i = 0; i < jsonArray.length(); i++)
				{
					YHUpdateEventScheduleVideoFaceThread2 thread = new YHUpdateEventScheduleVideoFaceThread2(this.i, i, event_schedule_video, jsonArray.getJSONObject(i), path);
					thread.start();
					/*JSONObject temp_jsonObject = jsonArray.getJSONObject(j);
					
					String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video.getEvent_schedule_video_id(), temp_jsonObject.getString("thumbnailId")), Reference.event_schedule_video_face_path);
					
					String face_id = YHMSFaceUtil.getFaceId(Reference.event_schedule_video_face_path, event_schedule_video_image_id);
					
					yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(face_id, event_schedule_video_image_id, event_schedule_video.getEvent_schedule_video_id());
					
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

					for(int i = 0; i < jsonArray.length(); i++)
					{
						if((boolean)YHUpdateEventScheduleVideoFaceThread2.out_map.get(this.i).get(i) == false)
						{
							continue out_while;
						}
					}
					break;
				}
			}
		}
		else if(36000000 < (new Date()).getTime() - event_schedule_video.getDetect_date())
		{	
			yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video.getEvent_schedule_video_id());
			
			yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video.getEvent_schedule_video_id());
			
			jsonObject = new JSONObject(YHMSVideoIndexerUtil.getVideoIndex(event_schedule_video.getEvent_schedule_video_id()));
			
			if(jsonObject.isNull("errorType"))
			{
				jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video.getEvent_schedule_video_id(), jsonArray.getJSONObject(j).getString("thumbnailId")), path + Reference.event_schedule_video_face_path);
					
					yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(YHMSFaceUtil.getFaceId(path + Reference.event_schedule_video_face_path, event_schedule_video_image_id), event_schedule_video_image_id, event_schedule_video.getEvent_schedule_video_id());
				}
			}		
		}
		
		out_map.get(index).put(this.i, true);
	}
}
