package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.User;
import project.ppaya.square.yhmapper.YHUserMapper;

@Repository
public class YHUserDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public User selectUserByUserId(String user_id)
	{
		User user = null;

		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			user = mapper.selectUserByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user;
	}
	public ArrayList<User> selectUserByUserIdList(ArrayList<String> user_id_list)
	{
		ArrayList<User> user_list = null;

		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			user_list = mapper.selectUserByUserIdList(user_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_list;
	}
}

