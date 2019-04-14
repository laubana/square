package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.Event;

public interface YHEventMapper
{
	public Event selectEventByEventId(int event_id);
	public ArrayList<Event> selectEventByEventIdList(ArrayList<Integer> event_id_list);
	public ArrayList<Event> selectEventByGroupId(int group_id);
	public ArrayList<Event> selectEventByGroupIdList(ArrayList<Integer> group_id_list);
	public ArrayList<Integer> getEventIdByGroupId(int group_id);
	public ArrayList<Integer> getEventIdByGroupIdList(ArrayList<Integer> group_id_list); 
}
