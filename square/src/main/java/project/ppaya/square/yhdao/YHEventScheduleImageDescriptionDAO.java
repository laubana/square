package project.ppaya.square.yhdao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleImageDescriptionDAO
{
	@Autowired
	SqlSession sqlSession;

	public String getDescriptionByEventScheduleImageId(String event_schedule_image_id)
	{
		String description = null;
		
		YHEventScheduleImageDescriptionMapper mapper = sqlSession.getMapper(YHEventScheduleImageDescriptionMapper.class);
		
		try
		{
			description = mapper.getDescriptionByEventScheduleImageId(event_schedule_image_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return description;
	}
	public int insertEventScheduleImageDescription(String event_schedule_image_id, String description)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("description", description);
		
		YHEventScheduleImageDescriptionMapper mapper = sqlSession.getMapper(YHEventScheduleImageDescriptionMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleImageDescription(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}
