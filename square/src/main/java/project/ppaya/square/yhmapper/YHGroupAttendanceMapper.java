package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupAttendance;

public interface YHGroupAttendanceMapper
{
	ArrayList<GroupAttendance> selectGroupAttendanceByUserId(String user_id);
	ArrayList<Integer> getGroupIdByUserId(String user_id);
}
