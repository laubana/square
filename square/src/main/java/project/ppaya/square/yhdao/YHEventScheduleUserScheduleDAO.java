package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleUserScheduleDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int deleteEventScheduleUserScheduleByUserIdEventScheduleId(String user_id, int event_schedule_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_schedule_id", event_schedule_id);
		
		YHEventScheduleUserScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleUserScheduleMapper.class);
		
		try
		{
			result = mapper.deleteEventScheduleUserScheduleByUserIdEventScheduleId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertEventScheduleUserSchedule(EventScheduleUserSchedule event_schedule_user_schedule)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", event_schedule_user_schedule.getUser_id());
		map.put("event_schedule_id", event_schedule_user_schedule.getEvent_schedule_id());
		map.put("start_date", event_schedule_user_schedule.getStart_date());
		map.put("end_date", event_schedule_user_schedule.getEnd_date());
		
		YHEventScheduleUserScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleUserScheduleMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleUserSchedule(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<EventScheduleUserSchedule> selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate
	(
			int event_schedule_id,
			String user_id,
			long start_date,
			long end_date
			)
	{
		ArrayList<EventScheduleUserSchedule> event_schedule_user_schedule_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_id", event_schedule_id);
		map.put("user_id", user_id);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		
		YHEventScheduleUserScheduleMapper mapper = sqlSession.getMapper(YHEventScheduleUserScheduleMapper.class);
		
		try
		{
			event_schedule_user_schedule_list = mapper.selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_user_schedule_list;
	}
}

