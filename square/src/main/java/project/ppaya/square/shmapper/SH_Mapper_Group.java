package project.ppaya.square.shmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupCategory;
import project.ppaya.square.vo.GroupHashtag;

public interface SH_Mapper_Group {

	public ArrayList<Group> getGroupByUser(String user);
	public ArrayList<Group> getGroupByCategory(int category);
	public ArrayList<Group> getGroupByKeyword(String keyword);
	public ArrayList<GroupCategory> getCategoryList();
	public int setGroupHashtag(GroupHashtag gh);
	
}
