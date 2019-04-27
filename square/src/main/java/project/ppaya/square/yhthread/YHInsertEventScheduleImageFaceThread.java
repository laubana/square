package project.ppaya.square.yhthread;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHInsertEventScheduleImageFaceThread extends Thread
{	
	public static ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	public static int index = 0;
	
	private String event_schedule_image_path;
	private String event_schedule_image_id;
	
	public YHInsertEventScheduleImageFaceThread(String event_schedule_image_path, String event_schedule_image_id)
	{
		this.event_schedule_image_path = event_schedule_image_path;
		this.event_schedule_image_id = event_schedule_image_id;
	}
	@Override
	public void run()
	{
		String result = YHMSFaceUtil.getFace(event_schedule_image_path, event_schedule_image_id);
		JSONArray jsonArray = new JSONArray(result);
		
		for(int j = 0; j < jsonArray.length(); j++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(j);
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("event_schedule_image_face_id", jsonObject.getString("faceId"));
			map.put("event_schedule_image_id", event_schedule_image_id);
			map.put("top", jsonObject.getJSONObject("faceRectangle").getInt("top"));
			map.put("left", jsonObject.getJSONObject("faceRectangle").getInt("left"));
			map.put("width", jsonObject.getJSONObject("faceRectangle").getInt("width"));
			map.put("height", jsonObject.getJSONObject("faceRectangle").getInt("height"));
			
			list.add(map);
		}
		
		index++;
	}
}
