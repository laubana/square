package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.Event;
import project.ppaya.square.yhmapper.YHEventMapper;
import project.ppaya.square.yhmapper.YHEventUnionMapper;

@Repository
public class YHEventUnionDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int deleteEventUnionByNotGroupIdEventId(int event_id, ArrayList<Integer> group_id_list)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("event_id", event_id);
		map.put("group_id_list", group_id_list);
		
		YHEventUnionMapper mapper = sqlSession.getMapper(YHEventUnionMapper.class);
		
		try
		{
			result = mapper.deleteEventUnionByNotGroupIdEventId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertEventUnion(int event_id, int group_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("event_id", event_id);
		map.put("group_id", group_id);
		
		YHEventUnionMapper mapper = sqlSession.getMapper(YHEventUnionMapper.class);
		
		try
		{
			result = mapper.insertEventUnion(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<Integer> getEventIdByGroupId(int group_id)
	{
		ArrayList<Integer> event_id_list = null;
		
		YHEventUnionMapper mapper = sqlSession.getMapper(YHEventUnionMapper.class);
		
		try
		{
			event_id_list = mapper.getEventIdByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_id_list;
	}
	public ArrayList<Integer> getGroupIdByEventId(int event_id)
	{
		ArrayList<Integer> group_id_list = null;
		
		YHEventUnionMapper mapper = sqlSession.getMapper(YHEventUnionMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
}

