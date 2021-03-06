package project.ppaya.square.shdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.shmapper.SH_Mapper_User;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupHashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;

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

public ArrayList<GroupHashtag> getUserHashtag(String userid){
	
	SH_Mapper_User umapper = session.getMapper(SH_Mapper_User.class);
	ArrayList<GroupHashtag> hlist = null;
	
	try
	{
		hlist = umapper.getUserHashtag(userid);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	return hlist;
}

public int inputUser(User user){
	
	SH_Mapper_User umapper = session.getMapper(SH_Mapper_User.class);
	int input_check = 0;
	
	try
	{
		input_check = umapper.inputUser(user);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	return input_check;
}


}
