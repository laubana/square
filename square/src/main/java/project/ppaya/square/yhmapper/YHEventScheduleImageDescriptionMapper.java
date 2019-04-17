package project.ppaya.square.yhmapper;

import java.util.HashMap;

import project.ppaya.square.vo.*;

public interface YHEventScheduleImageDescriptionMapper
{
	public String getDescriptionByEventScheduleImageId(String event_schedule_image_id);
	public int insertEventScheduleImageDescription(HashMap<String, Object> map);
}
