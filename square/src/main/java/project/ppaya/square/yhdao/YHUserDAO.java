package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public int updateUserImage(String user_id, String image_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("image_id", image_id);
		
		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			result = mapper.updateUserImage(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertUser(String user_id, String password, String name, String region)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("password", password);
		map.put("name", name);
		map.put("region", region);
		
		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			result = mapper.insertUser(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateContentByUserId(String user_id, String content)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("content", content);
		
		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			result = mapper.updateContentByUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<User> selectUser()
	{
		ArrayList<User> user_list = null;
		
		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			user_list = mapper.selectUser();
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_list;
	}
	public User selectUserByUserIdPassword(String user_id, String password)
	{
		User user = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("password", password);
		
		YHUserMapper mapper = sqlSession.getMapper(YHUserMapper.class);
		
		try
		{
			user = mapper.selectUserByUserIdPassword(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user;
	}
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

