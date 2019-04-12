package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.Group;

public interface YHGroupMapper
{
	public ArrayList<Group> selectGroupByGroupIdListNotGroupId(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryIdKeyword(HashMap<String, Object> map);
	public ArrayList<Group> selectGroupByGroupCategoryId(int group_category_id);
	public Group selectGroupByGroupId(int group_id);
	public ArrayList<Group> selectGroupByGroupIdList(ArrayList<Integer> group_id_list);
}
