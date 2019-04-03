package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.EventSchedule;

public interface YHEventScheduleMapper
{
	public ArrayList<EventSchedule> selectEventScheduleByEventId(int event_id);
	public ArrayList<EventSchedule> selectEventScheduleByEventIdList(ArrayList<Integer> event_id_list);
	public ArrayList<Integer> getEventScheduleIdByEventId(int event_id);
	public ArrayList<Integer> getEventScheduleIdByEventIdList(ArrayList<Integer> event_id_list);
}
