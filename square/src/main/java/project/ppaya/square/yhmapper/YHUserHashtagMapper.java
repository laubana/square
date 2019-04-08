package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;

public interface YHUserHashtagMapper
{
	public ArrayList<UserHashtag> selectUserHashTagByUserId(String user_id);
}
