package project.ppaya.square.shdao;

import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.ppaya.square.shmapper.SH_Mapper_Group;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupCategory;
import project.ppaya.square.vo.GroupHashtag;


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



public int setGroupHashtag( ArrayList<String> group_hashtag_list ) {
	int group_id = 1;
	int i = 0;
	int result = 0;
	for (i = 0; i < group_hashtag_list.size();  i=i + 1 ){
		SH_Mapper_Group gmapper = session.getMapper(SH_Mapper_Group.class);
		try {
			result = result + gmapper.setGroupHashtag( new GroupHashtag(group_hashtag_list.get(i), group_id) );
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	return result;
}


}
