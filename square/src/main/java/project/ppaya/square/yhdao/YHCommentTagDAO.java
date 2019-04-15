package project.ppaya.square.yhdao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHCommentTagDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertCommentTag(String user_id, String tag)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("tag", tag);
		
		YHCommentTagMapper mapper = sqlSession.getMapper(YHCommentTagMapper.class);
		
		try
		{
			result = mapper.insertCommentTag(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}

