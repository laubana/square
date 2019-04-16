package project.ppaya.square.yhdao;

import java.util.ArrayList;

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
