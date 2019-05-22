package project.ppaya.square.yhthread;

import java.util.HashMap;

import org.json.JSONObject;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHUpdateEventScheduleVideoFaceThread3 extends Thread
{	
	public static HashMap<Integer, HashMap<Integer, Object>> out_map = new HashMap<>();
	
	private YHVideoAppearanceDAO yh_video_appearanceDAO;
	
	private String face_id;
	private JSONObject jsonObject;
	private int index;
	private int i;
	
	public YHUpdateEventScheduleVideoFaceThread3(int index, int i, String face_id, JSONObject jsonObject)
	{
		yh_video_appearanceDAO = (YHVideoAppearanceDAO)YHBeanUtil.getBean("YHVideoAppearanceDAO");
		
		this.face_id = face_id;
		this.jsonObject = jsonObject;
		this.index = index;
		this.i = i;
		out_map.get(index).put(i, false);
	}
	@Override
	public void run()
	{		
		String string_start_time = jsonObject.getString("startTime");							
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
		
		String string_end_time = jsonObject.getString("endTime");							
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
		
		out_map.get(index).put(this.i, true);
	}
}
