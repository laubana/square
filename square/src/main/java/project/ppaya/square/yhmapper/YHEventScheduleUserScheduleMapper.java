package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventScheduleUserSchedule;

public interface YHEventScheduleUserScheduleMapper
{
	public int deleteEventScheduleUserScheduleByUserIdEventScheduleId(HashMap<String, Object> map);
	public int insertEventScheduleUserSchedule(HashMap<String, Object> map);
	public ArrayList<EventScheduleUserSchedule> selectEventScheduleUserScheduleByUserIdEventScheduleIdStartDateEndDate(HashMap<String, Object> map);
}
