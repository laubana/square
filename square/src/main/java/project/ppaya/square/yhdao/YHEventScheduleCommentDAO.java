package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.EventScheduleComment;
import project.ppaya.square.vo.GroupComment;
import project.ppaya.square.yhmapper.YHEventScheduleCommentMapper;
import project.ppaya.square.yhmapper.YHGroupCommentMapper;

@Repository
public class YHEventScheduleCommentDAO
{
	@Autowired
	SqlSession sqlSession;
	
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

