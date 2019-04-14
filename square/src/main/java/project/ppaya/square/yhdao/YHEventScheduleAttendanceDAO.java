package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertEventScheduleAttendance(String user_id, int event_schedule_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_schedule_id", event_schedule_id);
		
		YHEventScheduleAttendanceMapper mapper = sqlSession.getMapper(YHEventScheduleAttendanceMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleAttendance(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int deleteEventScheduleAttendanceByEventScheduleIdUserId(String user_id, int event_schedule_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_schedule_id", event_schedule_id);
		
		YHEventScheduleAttendanceMapper mapper = sqlSession.getMapper(YHEventScheduleAttendanceMapper.class);
		
		try
		{
			result = mapper.deleteEventScheduleAttendanceByEventScheduleIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public EventScheduleAttendance selectEventScheduleAttendanceByEventScheduleIdUserId(String user_id, int event_schedule_id)
	{
		EventScheduleAttendance event_schedule_attendance = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_schedule_id", event_schedule_id);
		
		YHEventScheduleAttendanceMapper mapper = sqlSession.getMapper(YHEventScheduleAttendanceMapper.class);
		
		try
		{
			event_schedule_attendance = mapper.selectEventScheduleAttendanceByEventScheduleIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_attendance;
	}
	public ArrayList<String> getUserIdByEventScheduleId(int event_schedule_id)
	{
		ArrayList<String> user_id_list = null;
		
		YHEventScheduleAttendanceMapper mapper = sqlSession.getMapper(YHEventScheduleAttendanceMapper.class);
		
		try
		{
			user_id_list = mapper.getUserIdByEventScheduleId(event_schedule_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_id_list;
	}
	public ArrayList<EventScheduleAttendance> selectEventScheduleAttendaceByUserId(String user_id)
	{
		ArrayList<EventScheduleAttendance> event_schedule_list = null;
		
		YHEventScheduleAttendanceMapper mapper = sqlSession.getMapper(YHEventScheduleAttendanceMapper.class);
		
		try
		{
			event_schedule_list = mapper.selectEventScheduleAttendaceByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_list;
	}
	public ArrayList<Integer> getEventScheduleIdByUserId(String user_id)
	{
		ArrayList<Integer> event_schedule_id_list = null;
		
		YHEventScheduleAttendanceMapper mapper = sqlSession.getMapper(YHEventScheduleAttendanceMapper.class);
		
		try
		{
			event_schedule_id_list = mapper.getEventScheduleIdByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_id_list;
	}
}
