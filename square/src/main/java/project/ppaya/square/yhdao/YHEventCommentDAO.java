package project.ppaya.square.yhdao;

import java.util.ArrayList;

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

