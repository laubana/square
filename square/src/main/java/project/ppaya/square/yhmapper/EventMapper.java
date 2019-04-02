package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.yhvo.Event;

public interface EventMapper
{
	public ArrayList<Event> selectEventByGroupId(int group_id);
	public ArrayList<Event> selectEventByGroupIdList(ArrayList<Integer> group_id_list);
	public ArrayList<Integer> getEventIdByGroupId(int group_id);
	public ArrayList<Integer> getEventIdByGroupIdList(ArrayList<Integer> group_id_list); 
}
