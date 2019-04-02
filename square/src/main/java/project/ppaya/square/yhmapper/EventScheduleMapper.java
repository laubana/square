package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.yhvo.EventSchedule;

public interface EventScheduleMapper
{
	public ArrayList<EventSchedule> selectEventScheduleByEventId(int event_id);
	public ArrayList<EventSchedule> selectEventScheduleByEventIdList(ArrayList<Integer> event_id_list);
	public ArrayList<Integer> getEventScheduleIdByEventId(int event_id);
	public ArrayList<Integer> getEventScheduleIdByEventIdList(ArrayList<Integer> event_id_list);
}
