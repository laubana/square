package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.Event;

public interface YHEventUnionMapper
{
	public int insertEventUnion(HashMap<String, Object> map);
	public ArrayList<Integer> getEventIdByGroupId(int group_id);
	public ArrayList<Integer> getGroupIdByEventId(int event_id);
	public int deleteEventUnionByNotGroupIdEventId(HashMap<String, Object> map);
}
