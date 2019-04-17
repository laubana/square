package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleCommentDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public EventScheduleComment selectEventScheduleCommentByEventScheduleCommentId(int event_schedule_comment_id)
	{
		EventScheduleComment event_schedule_comment = null;
		
		YHEventScheduleCommentMapper mapper = sqlSession.getMapper(YHEventScheduleCommentMapper.class);
		
		try
		{
			event_schedule_comment = mapper.selectEventScheduleCommentByEventScheduleCommentId(event_schedule_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_comment;
	}
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		ArrayList<EventScheduleComment> event_schedule_comment_list = null;
		
		YHEventScheduleCommentMapper mapper = sqlSession.getMapper(YHEventScheduleCommentMapper.class);
		
		try
		{
			event_schedule_comment_list = mapper.selectEventScheduleCommentByEventScheduleIdList(event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_comment_list;
	}
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleId(int event_schedule_id)
	{
		ArrayList<EventScheduleComment> event_schedule_comment_list = null;
		
		YHEventScheduleCommentMapper mapper = sqlSession.getMapper(YHEventScheduleCommentMapper.class);
		
		try
		{
			event_schedule_comment_list = mapper.selectEventScheduleCommentByEventScheduleId(event_schedule_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_comment_list;
	}
}

