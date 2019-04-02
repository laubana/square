package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.yhvo.Group;

public interface GroupMapper
{
	public ArrayList<Group> selectGroupByGroupIdList(ArrayList<Integer> group_id_list);
}
