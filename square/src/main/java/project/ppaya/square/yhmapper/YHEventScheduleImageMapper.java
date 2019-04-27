package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventScheduleImage;

public interface YHEventScheduleImageMapper
{
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventId(int event_id);
	public int updateDescriptionByEventScheduleImageId(HashMap<String, Object> map);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByGroupId(int group_id);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByGroupIdListOrderByInputdate(HashMap<String, Object> map);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleIdListOrderByInputdate(HashMap<String, Object> map);
	public ArrayList<EventScheduleImage> selectEventSchedeuleImageOrderByInputdate(int flag);
	public int insertEventScheduleImage(HashMap<String, Object> map);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleId(int event_schedule_id);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public int updateEventScheduleImageDetectDateByEventScheduleImageId(HashMap<String, Object> map);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleId(int event_schedule_id);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleImageId(String event_schedule_image_id);
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleImageIdList(ArrayList<String> event_schedule_image_id_list);
}
