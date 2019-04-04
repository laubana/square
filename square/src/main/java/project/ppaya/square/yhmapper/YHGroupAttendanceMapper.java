package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupAttendance;

public interface YHGroupAttendanceMapper
{
	public ArrayList<String> getUserIdByGroupId(int group_id);
	public ArrayList<GroupAttendance> selectGroupAttendanceByGroupId(int group_id);
	public ArrayList<GroupAttendance> selectGroupAttendanceByUserId(String user_id);
	public ArrayList<Integer> getGroupIdByUserId(String user_id);
}
