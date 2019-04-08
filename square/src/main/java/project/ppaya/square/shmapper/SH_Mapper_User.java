package project.ppaya.square.shmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.Hashtag;
import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;

public interface SH_Mapper_User {

	
	public User getUserByEmail(String email);
	public ArrayList<Hashtag> getUserHashtag(String userid);
	public int inputUser(User user);

}
