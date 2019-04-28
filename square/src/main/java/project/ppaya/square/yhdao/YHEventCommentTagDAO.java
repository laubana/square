package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventCommentTagDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int deleteEventCommentByEventCommentId(int event_comment_id)
	{
		int result = 0;
		
		YHEventCommentTagMapper mapper = sqlSession.getMapper(YHEventCommentTagMapper.class);
		
		try
		{
			result = mapper.deleteEventCommentByEventCommentId(event_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<String> getTagByEventCommentId(int event_comment_id)
	{
		ArrayList<String> tag_list = null;
		
		YHEventCommentTagMapper mapper = sqlSession.getMapper(YHEventCommentTagMapper.class);
		
		try
		{
			tag_list = mapper.getTagByEventCommentId(event_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return tag_list;
	}
	public int insertEventCommentTag(int event_comment_id, String tag)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_comment_id", event_comment_id);
		map.put("tag", tag);
		
		YHEventCommentTagMapper mapper = sqlSession.getMapper(YHEventCommentTagMapper.class);
		
		try
		{
			result = mapper.insertEventCommentTag(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}

