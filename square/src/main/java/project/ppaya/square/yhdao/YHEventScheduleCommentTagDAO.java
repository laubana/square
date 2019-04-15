package project.ppaya.square.yhdao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleCommentTagDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertEventScheduleCommentTag(int event_schedule_comment_id, String tag)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_comment_id", event_schedule_comment_id);
		map.put("tag", tag);
		
		YHEventScheduleCommentTagMapper mapper = sqlSession.getMapper(YHEventScheduleCommentTagMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleCommentTag(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}

