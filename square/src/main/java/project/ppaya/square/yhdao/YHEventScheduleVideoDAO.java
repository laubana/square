package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleVideoDAO
{
	@Autowired
	SqlSession sqlSession;

	public ArrayList<EventScheduleVideo> selectEventScheduleVideoByEventScheduleId(int event_schedule_id)
	{
		ArrayList<EventScheduleVideo> event_schedule_video_list = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video_list = mapper.selectEventScheduleVideoByEventScheduleId(event_schedule_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_list;
	}
	public ArrayList<EventScheduleVideo> selectEventScheduleVideoByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		ArrayList<EventScheduleVideo> event_schedule_video_list = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video_list = mapper.selectEventScheduleVideoByEventScheduleIdList(event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_list;
	}
	public ArrayList<EventScheduleVideo> selectEventScheduleVideoByEventScheduleVideoIdList(ArrayList<String> event_schedule_video_id_list)
	{
		ArrayList<EventScheduleVideo> event_schedule_video_list = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video_list = mapper.selectEventScheduleVideoByEventScheduleVideoIdList(event_schedule_video_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_list;
	}
	public int insertEventScheduleVideo
	(
			String event_schedule_video_id,
			String filename,
			String ext,
			long detect_date,
			String user_id,
			int group_category_id,
			int group_id,
			int event_id,
			int event_schedule_id
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_id", event_schedule_video_id);
		map.put("filename", filename);
		map.put("ext", ext);
		map.put("detect_date", detect_date);
		map.put("user_id", user_id);
		map.put("group_category_id", group_category_id);
		map.put("group_id", group_id);
		map.put("event_id", event_id);
		map.put("event_schedule_id", event_schedule_id);
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleVideo(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateDetectDateByEventScheduleVideoId(long detect_date, String event_schedule_video_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("detect_date", detect_date);
		map.put("event_schedule_video_id", event_schedule_video_id);
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			result = mapper.updateDetectDateByEventScheduleVideoId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public EventScheduleVideo selectEventScheduleVideoByEventScheduleVideoId(String event_schedule_video_id)
	{
		EventScheduleVideo event_schedule_video = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video = mapper.selectEventScheduleVideoByEventScheduleVideoId(event_schedule_video_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video;
	}
	public ArrayList<String> getEventScheduleVideoIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		ArrayList<String> event_schedule_video_id_list = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video_id_list = mapper.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_id_list;
	}
}
