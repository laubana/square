package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventSchedule;

public interface YHEventScheduleMapper
{	
	public ArrayList<Integer> getEventScheduleIdByGroupIdListEventScheduleIdList(HashMap<String, Object> map);
	public ArrayList<Integer> getEventScheduleIdByGroupId(int group_id);
	public ArrayList<Integer> getEventScheduleIdByGroupIdList(ArrayList<Integer> group_id_list);
	public ArrayList<EventSchedule> selectEventScheduleByGroupId(int group_id);
	public ArrayList<EventSchedule> selectEventScheduleByGroupIdList(ArrayList<Integer> group_id_list);
	public int insertEventSchedule(HashMap<String, Object> map);
	public ArrayList<Integer> getEventScheduleIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public EventSchedule selectEventScheduleByEventScheduleId(int event_schedule_id);
	public ArrayList<EventSchedule> selectEventScheduleByEventId(int event_id);
	public ArrayList<EventSchedule> selectEventScheduleByEventIdList(ArrayList<Integer> event_id_list);
	public ArrayList<Integer> getEventScheduleIdByEventId(int event_id);
	public ArrayList<Integer> getEventScheduleIdByEventIdList(ArrayList<Integer> event_id_list);
}
