package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.EventScheduleAttendance;
import project.ppaya.square.yhmapper.YHEventScheduleAttendanceMapper;

@Repository
public class YHEventScheduleAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
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
