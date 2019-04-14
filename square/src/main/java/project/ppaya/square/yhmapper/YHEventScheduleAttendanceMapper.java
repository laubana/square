package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventScheduleAttendance;

public interface YHEventScheduleAttendanceMapper
{
	public int insertEventScheduleAttendance(HashMap<String, Object> map);
	public int deleteEventScheduleAttendanceByEventScheduleIdUserId(HashMap<String, Object> map);
	public EventScheduleAttendance selectEventScheduleAttendanceByEventScheduleIdUserId(HashMap<String, Object> map);
	public ArrayList<String> getUserIdByEventScheduleId(int event_schedule_id);
	public ArrayList<EventScheduleAttendance> selectEventScheduleAttendaceByUserId(String user_id);
	public ArrayList<Integer> getEventScheduleIdByUserId(String user_id);
}
