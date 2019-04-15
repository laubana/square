package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleImageCategoryDAO
{
	@Autowired
	SqlSession sqlSession;

	public int insertEventScheduleImageCategory(String event_schedule_image_id, String category)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("category", category);
		
		YHEventScheduleImageCategoryMapper mapper = sqlSession.getMapper(YHEventScheduleImageCategoryMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleImageCategory(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}
