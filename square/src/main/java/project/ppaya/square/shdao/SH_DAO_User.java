package project.ppaya.square.shdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.shmapper.SH_Mapper_User;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.User;

@Repository
public class SH_DAO_User {
	
	
@Autowired
SqlSession session;

public int loginCheck(String email, String password){
	
	SH_Mapper_User umapper = session.getMapper(SH_Mapper_User.class);
	User user = umapper.getUserByEmail(email);
	boolean check = user.getPassword().equals(password);
	if(check){
		return 1;
	}
	else{
		return 0;
	}
}


public ArrayList<Group> getGroupByUser(String user){
	SH_Mapper_User umapper = session.getMapper(SH_Mapper_User.class);
	ArrayList<Group> glist = new ArrayList<>();
	
	try
	{
		glist = umapper.getGroupByUser(user);
	}
	catch(Exception error){error.printStackTrace();}
	return glist;
}

}
