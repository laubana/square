package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.EventScheduleAttendance;

public interface YHEventScheduleAttendanceMapper
{
	public ArrayList<EventScheduleAttendance> selectEventScheduleAttendaceByUserId(String user_id);
	public ArrayList<Integer> getEventScheduleIdByUserId(String user_id);
}
