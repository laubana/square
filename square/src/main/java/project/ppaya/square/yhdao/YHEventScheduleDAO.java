package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<Integer> getEventScheduleIdByGroupId(int group_id)
	{
		ArrayList<Integer> event_schedule_id_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_id_list = mapper.getEventScheduleIdByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_id_list;
	}
	public ArrayList<Integer> getEventScheduleIdByGroupIdList(ArrayList<Integer> group_id_list)
	{
		ArrayList<Integer> event_schedule_id_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_id_list = mapper.getEventScheduleIdByGroupIdList(group_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_id_list;
	}
	public ArrayList<EventSchedule> selectEventScheduleByGroupId(int group_id)
	{
		ArrayList<EventSchedule> event_schedule_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_list = mapper.selectEventScheduleByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_list;
	}
	public ArrayList<EventSchedule> selectEventScheduleByGroupIdList(ArrayList<Integer> group_id_list)
	{
		ArrayList<EventSchedule> event_schedule_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_list = mapper.selectEventScheduleByGroupIdList(group_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_list;
	}
	public int insertEventSchedule(int group_id, int event_id, String name, String content, String region, String address, String latitude, String longitude, long start_date, long end_date)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_id", group_id);
		map.put("event_id", event_id);
		map.put("name", name);
		map.put("content", content);
		map.put("region", region);
		map.put("address", address);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			result = mapper.insertEventSchedule(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<Integer> getEventScheduleIdByEventScheduleIdList(ArrayList<Integer> old_event_schedule_id_list)
	{
		ArrayList<Integer> new_event_schedule_id_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			new_event_schedule_id_list = mapper.getEventScheduleIdByEventScheduleIdList(old_event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return new_event_schedule_id_list;
	}
	public EventSchedule selectEventScheduleByEventScheduleId(int event_schedule_id)
	{	
		EventSchedule event_schedule = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule = mapper.selectEventScheduleByEventScheduleId(event_schedule_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule;
	}
	public ArrayList<EventSchedule> selectEventScheduleByEventId(int event_id)
	{
		ArrayList<EventSchedule> event_schedule_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_list = mapper.selectEventScheduleByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_list;
	}
	public ArrayList<EventSchedule> selectEventScheduleByEventIdList(ArrayList<Integer> event_id_list)
	{
		ArrayList<EventSchedule> event_schedule_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_list = mapper.selectEventScheduleByEventIdList(event_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_list;
	}
	public ArrayList<Integer> getEventScheduleIdByEventId(int event_id)
	{
		ArrayList<Integer> event_schedule_id_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_id_list = mapper.getEventScheduleIdByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_id_list;
	}
	public ArrayList<Integer> getEventScheduleIdByEventIdList(ArrayList<Integer> event_id_list)
	{
		ArrayList<Integer> event_schedule_id_list = null;
		
		YHEventScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleMapper.class);
		
		try
		{
			event_schedule_id_list = mapper.getEventScheduleIdByEventIdList(event_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_id_list;
	}
}
