package project.ppaya.square.yhdao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHLogDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertLog(String message)
	{
		int result = 0;
		
		YHLogMapper mapper = sqlSession.getMapper(YHLogMapper.class);
		
		try
		{
			result = mapper.insertLog(message);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}

