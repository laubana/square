package project.ppaya.square.yhthread;

import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHUpdateEventScheduleVideoFaceThread2 extends Thread
{	
	public static HashMap<Integer, HashMap<Integer, Object>> out_map = new HashMap<>();
	
	private YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	
	private EventScheduleVideo event_schedule_video;
	private JSONObject jsonObject;
	private int index;
	private int i;
	
	public YHUpdateEventScheduleVideoFaceThread2(int index, int i, EventScheduleVideo event_schedule_video, JSONObject jsonObject)
	{
		yh_event_schedule_video_faceDAO = (YHEventScheduleVideoFaceDAO)YHBeanUtil.getBean("YHEventScheduleVideoFaceDAO");
		
		this.event_schedule_video = event_schedule_video;
		this.jsonObject = jsonObject;
		this.index = index;
		this.i = i;
		out_map.get(index).put(i, false);
	}
	@Override
	public void run()
	{
		String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHMSVideoIndexerUtil.getThumbnail(event_schedule_video.getEvent_schedule_video_id(), jsonObject.getString("thumbnailId")), Reference.event_schedule_video_face_path);
		
		String face_id = YHMSFaceUtil.getFaceId(Reference.event_schedule_video_face_path, event_schedule_video_image_id);
		
		yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(face_id, event_schedule_video_image_id, event_schedule_video.getEvent_schedule_video_id());
		
		JSONArray jsonArray = jsonObject.getJSONArray("appearances");
		
		YHUpdateEventScheduleVideoFaceThread3.out_map.put(this.i, new HashMap<>());
		for(int i = 0; i < jsonArray.length(); i++)
		{
			YHUpdateEventScheduleVideoFaceThread3 thread = new YHUpdateEventScheduleVideoFaceThread3(this.i, i, face_id, jsonArray.getJSONObject(i));
			thread.start();
			/*JSONObject temp_jsonObject = temp_jsonArray.getJSONObject(k);
			
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
			
			yh_video_appearanceDAO.insertVideoAppearance(face_id, start_time, end_time);*/
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
				if((boolean)YHUpdateEventScheduleVideoFaceThread3.out_map.get(this.i).get(i) == false)
				{
					continue out_while;
				}
			}
			break;
		}
		
		out_map.get(index).put(this.i, true);
	}
}
