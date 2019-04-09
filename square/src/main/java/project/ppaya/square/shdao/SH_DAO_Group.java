package project.ppaya.square.shdao;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.ppaya.square.shmapper.SH_Mapper_Group;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupCategory;


@Repository
public class SH_DAO_Group {

@Autowired
SqlSession session;

public ArrayList<Group> getGroupByUser(String user){
	SH_Mapper_Group gmapper = session.getMapper(SH_Mapper_Group.class);
	ArrayList<Group> glist = new ArrayList<>();
	
	try
	{
		glist = gmapper.getGroupByUser(user);
	}
	catch(Exception error){error.printStackTrace();}
	return glist;
}

public ArrayList<Group> getGroupByCategory(int category){
	SH_Mapper_Group gmapper = session.getMapper(SH_Mapper_Group.class);
	ArrayList<Group> glist = null;
	
	try
	{
		glist = gmapper.getGroupByCategory(category);
	}
	catch(Exception error){error.printStackTrace();}
	return glist;
}


public ArrayList<GroupCategory> getCategoryList(){
	SH_Mapper_Group gmapper = session.getMapper(SH_Mapper_Group.class);
	ArrayList<GroupCategory> clist = null;
	
	try
	{
		clist = gmapper.getCategoryList();
	}
	catch(Exception error){error.printStackTrace();}
	return clist;
}

public ArrayList<Group> getGroupByKeyword(String keyword){
	SH_Mapper_Group gmapper = session.getMapper(SH_Mapper_Group.class);
	ArrayList<Group> glist = null;
	try
	{
		glist = gmapper.getGroupByKeyword(keyword);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return glist;
	
	
}


}