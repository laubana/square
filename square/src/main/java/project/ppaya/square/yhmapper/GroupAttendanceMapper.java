package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.yhvo.GroupAttendance;

public interface GroupAttendanceMapper
{
	ArrayList<GroupAttendance> selectGroupAttendanceByUserId(String user_id);
}
