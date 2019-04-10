package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.GroupAttendance;

public interface YHGroupAttendanceMapper
{
	public int insertGroupAttendance(HashMap<String, Object> map);
	public int deleteGroupAttendanceByGroupIdUserId(HashMap<String, Object> map);
	public GroupAttendance selectGroupAttendanceByGroupIdUserId(HashMap<String, Object> map);
	public ArrayList<String> getUserIdByGroupId(int group_id);
	public ArrayList<GroupAttendance> selectGroupAttendanceByGroupId(int group_id);
	public ArrayList<GroupAttendance> selectGroupAttendanceByUserId(String user_id);
	public ArrayList<Integer> getGroupIdByUserId(String user_id);
	public ArrayList<Integer> getGroupIdByUserIdNotBlind(String user_id);
}
