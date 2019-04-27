package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventCommentDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int deleteEventCommentByEventCommentIdUserId(int event_comment_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_comment_id", event_comment_id);
		map.put("user_id", user_id);
		
		YHEventCommentMapper mapper = sqlSession.getMapper(YHEventCommentMapper.class);	
		
		try
		{
			result = mapper.deleteEventCommentByEventCommentIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateContentByEventCommentIdUserId(int event_comment_id, String user_id, String content)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_comment_id", event_comment_id);
		map.put("user_id", user_id);
		map.put("content", content);
		
		YHEventCommentMapper mapper = sqlSession.getMapper(YHEventCommentMapper.class);	
		
		try
		{
			result = mapper.updateContentByEventCommentIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public EventComment selectEventCommentByEventCommentId(int event_comment_id)
	{
		EventComment event_comment = null;
		
		YHEventCommentMapper mapper = sqlSession.getMapper(YHEventCommentMapper.class);
		
		try
		{
			event_comment = mapper.selectEventCommentByEventCommentId(event_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_comment;
	}
	public ArrayList<EventComment> selectEventCommentByEventId(int event_id)
	{
		ArrayList<EventComment> event_comment_list = null;
		
		YHEventCommentMapper mapper = sqlSession.getMapper(YHEventCommentMapper.class);
		
		try
		{
			event_comment_list = mapper.selectEventCommentByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_comment_list;
	}
	public ArrayList<EventComment> selectEventCommentByEventIdList(ArrayList<Integer> event_id_list)
	{
		ArrayList<EventComment> event_comment_list = null;
		
		YHEventCommentMapper mapper = sqlSession.getMapper(YHEventCommentMapper.class);
		
		try
		{
			event_comment_list = mapper.selectEventCommentByEventIdList(event_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_comment_list;
	}
}

