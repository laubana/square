package project.ppaya.square.shmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.Group;

public interface SH_Mapper_Group {

	public ArrayList<Group> getGroupByUser(String user);
	public ArrayList<Group> getGroupByCategory(int category);
	
}
