package project.ppaya.square.yhdao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.yhmapper.UserMapper;
import project.ppaya.square.yhvo.User;

@Repository
public class UserDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public User selectUserByUserId(String user_id)
	{
		User user = null;

		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		try
		{
			user = mapper.selectUserByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user;
	}
}

