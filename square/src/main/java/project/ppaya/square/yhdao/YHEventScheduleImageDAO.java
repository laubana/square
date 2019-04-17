package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleImageDAO
{
	@Autowired
	SqlSession sqlSession;

	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleIdListOrderByInputdate(ArrayList<Integer> event_schedule_id_list, int flag)
	{
		ArrayList<EventScheduleImage> event_schedule_image_list = null;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("event_schedule_id_list", event_schedule_id_list);
		map.put("flag", flag);
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_list = mapper.selectEventScheduleImageByEventScheduleIdListOrderByInputdate(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_list;
	}
	public ArrayList<EventScheduleImage> selectEventSchedeuleImageOrderByInputdate(int flag)
	{
		ArrayList<EventScheduleImage> event_schedule_image_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_list = mapper.selectEventSchedeuleImageOrderByInputdate(flag);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_list;
	}
	public int insertEventScheduleImage
	(
			String event_schedule_image_id,
			String user_id,
			int event_schedule_id,
			String filename,
			String ext
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("user_id", user_id);
		map.put("event_schedule_id", event_schedule_id);
		map.put("filename", filename);
		map.put("ext", ext);
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleImage(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleId(int event_schedule_id)
	{
		ArrayList<EventScheduleImage> event_schedule_image_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_list = mapper.selectEventScheduleImageByEventScheduleId(event_schedule_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_list;
	}
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		ArrayList<EventScheduleImage> event_schedule_image_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_list = mapper.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_list;
	}
	public int updateEventScheduleImageDetectDateByEventScheduleImageId(String event_schedule_image_id, long detect_date)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("detect_date", detect_date);
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			result = mapper.updateEventScheduleImageDetectDateByEventScheduleImageId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<String> getEventScheduleImageIdByEventScheduleId(int event_schedule_id)
	{
		ArrayList<String> event_schedule_image_id_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_id_list = mapper.getEventScheduleImageIdByEventScheduleId(event_schedule_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_id_list;
	}
	public ArrayList<String> getEventScheduleImageIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		ArrayList<String> event_schedule_image_id_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_id_list = mapper.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_id_list;
	}
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleImageId(String event_schedule_image_id)
	{
		ArrayList<EventScheduleImage> event_schedule_image_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_list = mapper.selectEventScheduleImageByEventScheduleImageId(event_schedule_image_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_list;
	}
	public ArrayList<EventScheduleImage> selectEventScheduleImageByEventScheduleImageIdList(ArrayList<String> event_schedule_image_id_list)
	{
		ArrayList<EventScheduleImage> event_schedule_image_list = null;
		
		YHEventScheduleImageMapper mapper = sqlSession.getMapper(YHEventScheduleImageMapper.class);
		
		try
		{
			event_schedule_image_list = mapper.selectEventScheduleImageByEventScheduleImageIdList(event_schedule_image_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_list;
	}
}
