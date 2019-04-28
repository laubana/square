package project.ppaya.square.yhthread;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHUpdateEventScheduleImageFaceThread extends Thread
{	
	private YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	private YHEventScheduleImageFaceDAO yh_event_schedule_image_faceDAO;
	public static int index = 0;
	private EventScheduleImage event_schedule_image;
	
	public YHUpdateEventScheduleImageFaceThread(EventScheduleImage event_schedule_image)
	{
		yh_event_schedule_imageDAO = (YHEventScheduleImageDAO)YHBeanUtil.getBean("YHEventScheduleImageDAO");
		yh_event_schedule_image_faceDAO = (YHEventScheduleImageFaceDAO)YHBeanUtil.getBean("YHEventScheduleImageFaceDAO");
		this.event_schedule_image = event_schedule_image;
	}
	@Override
	public void run()
	{
		String result;
		JSONArray jsonArray;
		JSONObject jsonObject;
		
		if(event_schedule_image.getDetect_date() == null)
		{
			yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image.getEvent_schedule_image_id(), (new Date()).getTime());
			
			result = YHMSFaceUtil.getFace(Reference.event_schedule_image_path, event_schedule_image.getEvent_schedule_image_id());
			jsonArray = new JSONArray(result);
			
			for(int j = 0; j < jsonArray.length(); j++)
			{
				jsonObject = jsonArray.getJSONObject(j);
				
				yh_event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image.getEvent_schedule_image_id(),
						jsonObject.getJSONObject("faceRectangle").getInt("top"),
						jsonObject.getJSONObject("faceRectangle").getInt("left"),
						jsonObject.getJSONObject("faceRectangle").getInt("width"),
						jsonObject.getJSONObject("faceRectangle").getInt("height")
						);
			}
		}
		else if(3600000 < (new Date()).getTime() - event_schedule_image.getDetect_date())
		{
			yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image.getEvent_schedule_image_id(), (new Date()).getTime());
			
			yh_event_schedule_image_faceDAO.deleteEventScheduleImageFaceByEventScheduleImageId(event_schedule_image.getEvent_schedule_image_id());
			
			result = YHMSFaceUtil.getFace(Reference.event_schedule_image_path, event_schedule_image.getEvent_schedule_image_id());
			jsonArray = new JSONArray(result);
			
			for(int j = 0; j < jsonArray.length(); j++)
			{
				jsonObject = jsonArray.getJSONObject(j);
				
				yh_event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image.getEvent_schedule_image_id(),
						jsonObject.getJSONObject("faceRectangle").getInt("top"),
						jsonObject.getJSONObject("faceRectangle").getInt("left"),
						jsonObject.getJSONObject("faceRectangle").getInt("width"),
						jsonObject.getJSONObject("faceRectangle").getInt("height")
						);
			}
		}
		
		index++;
	}
}
