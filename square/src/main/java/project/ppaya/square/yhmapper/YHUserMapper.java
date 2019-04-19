package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.User;

public interface YHUserMapper
{
	public int updateContentByUserId(HashMap<String, Object> map);
	public ArrayList<User> selectUser();
	public User selectUserByUserIdPassword(HashMap<String, Object> map);
	public User selectUserByUserId(String user_id);
	public ArrayList<User> selectUserByUserIdList(ArrayList<String> user_id_list);
}
