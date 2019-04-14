package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertEventAttendance(String user_id, int event_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_id", event_id);
		
		YHEventAttendanceMapper mapper = sqlSession.getMapper(YHEventAttendanceMapper.class);
		
		try
		{
			result = mapper.insertEventAttendance(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int deleteEventAttendanceByEventIdUserId(String user_id, int event_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_id", event_id);
		
		YHEventAttendanceMapper mapper = sqlSession.getMapper(YHEventAttendanceMapper.class);
		
		try
		{
			result = mapper.deleteEventAttendanceByEventIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public EventAttendance selectEventAttendanceByEventIdUserId(String user_id, int event_id)
	{
		EventAttendance event_attendance = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_id", event_id);
		
		YHEventAttendanceMapper mapper = sqlSession.getMapper(YHEventAttendanceMapper.class);
		
		try
		{
			event_attendance = mapper.selectEventAttendanceByEventIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_attendance;
	}
	public ArrayList<String> getUserIdByEventId(int event_id)
	{
		ArrayList<String> user_id_list = null;
		
		YHEventAttendanceMapper mapper = sqlSession.getMapper(YHEventAttendanceMapper.class);
		
		try
		{
			user_id_list = mapper.getUserIdByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_id_list;
	}
}

