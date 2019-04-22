package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertEvent(String name, String content, String user_id, int group_category_id, int group_id, String image_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("content", content);
		map.put("user_id", user_id);
		map.put("group_category_id", group_category_id);
		map.put("group_id", group_id);
		map.put("image_id", image_id);
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			result = mapper.insertEvent(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public Event selectEventByEventId(int event_id)
	{
		Event event = null;
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			event = mapper.selectEventByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event;
	}
	public ArrayList<Event> selectEventByEventIdList(ArrayList<Integer> event_id_list)
	{
		ArrayList<Event> event_list = null;
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			event_list = mapper.selectEventByEventIdList(event_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_list;
	}
	public ArrayList<Event> selectEventByGroupId(int group_id)
	{
		ArrayList<Event> event_list = null;
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			event_list = mapper.selectEventByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_list;
	}
	public ArrayList<Event> selectEventByGroupIdList(ArrayList<Integer> group_id_list)
	{
		ArrayList<Event> event_list = null;
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			event_list = mapper.selectEventByGroupIdList(group_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_list;
	}
	public ArrayList<Integer> getEventIdByGroupId(int group_id)
	{
		ArrayList<Integer> event_id_list = null;
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			event_id_list = mapper.getEventIdByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_id_list;
	}
	public ArrayList<Integer> getEventIdByGroupIdList(ArrayList<Integer> group_id_list)
	{
		ArrayList<Integer> event_id_list = null;
		
		YHEventMapper mapper = sqlSession.getMapper(YHEventMapper.class);
		
		try
		{
			event_id_list = mapper.getEventIdByGroupIdList(group_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_id_list;
	}
}

