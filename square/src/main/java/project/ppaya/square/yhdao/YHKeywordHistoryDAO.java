package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHKeywordHistoryDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<String> getKeywordByRank()
	{
		ArrayList<String> keyword_list = null;
		
		YHKeywordHistoryMapper mapper = sqlSession.getMapper(YHKeywordHistoryMapper.class);
		
		try
		{
			keyword_list = mapper.getKeywordByRank();
		}
		catch(Exception error){error.printStackTrace();}
		
		return keyword_list;
	}
	public int insertKeywordHistory(String keyword)
	{
		int result = 0;
		
		YHKeywordHistoryMapper mapper = sqlSession.getMapper(YHKeywordHistoryMapper.class);
		
		try
		{
			result = mapper.insertKeywordHistory(keyword);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}

