package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventAttendance;
import project.ppaya.square.vo.GroupAttendance;

public interface YHEventAttendanceMapper
{
	public int insertEventAttendance(HashMap<String, Object> map);
	public int deleteEventAttendanceByEventIdUserId(HashMap<String, Object> map);
	public EventAttendance selectEventAttendanceByEventIdUserId(HashMap<String, Object> map);
	public ArrayList<String> getUserIdByEventId(int event_id);
}
