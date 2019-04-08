package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;
import project.ppaya.square.yhmapper.YHUserHashtagMapper;
import project.ppaya.square.yhmapper.YHUserMapper;

@Repository
public class YHUserHashtagDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<UserHashtag> selectUserHashtagByUserId(String user_id)
	{
		ArrayList<UserHashtag> user_hashtag_list = null;

		YHUserHashtagMapper mapper = sqlSession.getMapper(YHUserHashtagMapper.class);
		
		try
		{
			user_hashtag_list = mapper.selectUserHashTagByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_hashtag_list;
	}
}

