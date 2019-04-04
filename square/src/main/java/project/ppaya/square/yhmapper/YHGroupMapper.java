package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.Group;

public interface YHGroupMapper
{
	public Group selectGroupByGroupId(int group_id);
	public ArrayList<Group> selectGroupByGroupIdList(ArrayList<Integer> group_id_list);
}
