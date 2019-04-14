package project.ppaya.square.yhdao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleImageTagDAO
{
	@Autowired
	SqlSession sqlSession;

	public int insertEventScheduleImageTag(String event_schedule_image_id, String tag)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("tag", tag);
		
		YHEventScheduleImageTagMapper mapper = sqlSession.getMapper(YHEventScheduleImageTagMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleImageTag(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}
