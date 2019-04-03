package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventScheduleImage;

public interface YHEventScheduleImageMapper
{
	public int insertEventScheduleImage(HashMap<String, Object> map);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleId(int event_schedule_id);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public int updateEventScheduleImageDetectDateByEventScheduleImageId(HashMap<String, Object> map);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleId(int event_schedule_id);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleImageId(String event_schedule_image_id);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleImageIdList(ArrayList<String> event_schedule_image_id_list);
}
