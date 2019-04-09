package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupAttendance;

public interface YHEventAttendanceMapper
{
	public ArrayList<String> getUserIdByEventId(int event_id);
}
