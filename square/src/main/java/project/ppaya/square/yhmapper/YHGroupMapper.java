package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.Group;

public interface YHGroupMapper
{
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByInputdate(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByEventScheduleCount(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByEventCount(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByGroupAttendanceCount(HashMap<String, Object> map);
	public int insertGroup(HashMap<String, Object> map);
	public ArrayList<Integer> getGroupIdByName(String name);
	public ArrayList<Group> selectGroupByName(String name);
	public ArrayList<Group> selectGroupByGroupIdListNotGroupId(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryIdName(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryId(int group_category_id);
	public Group selectGroupByGroupId(int group_id);
	public ArrayList<Group> selectGroupByGroupIdList(ArrayList<Integer> group_id_list);
}
