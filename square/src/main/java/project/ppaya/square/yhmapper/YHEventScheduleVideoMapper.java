package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventScheduleVideo;

public interface YHEventScheduleVideoMapper
{
	public int insertEventScheduleVideo(HashMap<String, Object> map);
	public int updateDetectDateByEventScheduleVideoId(HashMap<String, Object> map);
	public EventScheduleVideo selectEventScheduleVideoByEventScheduleVideoId(String event_schedule_video_id);
	public ArrayList<String> getEventScheduleVideoIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public ArrayList<EventScheduleVideo> selectEventScheduleVideoByEventScheduleVideoIdList(ArrayList<String> event_schedule_video_id_list);
	public ArrayList<EventScheduleVideo> selectEventScheduleVideoByEventScheduleId(int event_schedule_id);
	public ArrayList<EventScheduleVideo> selectEventScheduleVideoByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
}
