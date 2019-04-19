package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHGroupDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByInputdate(int group_category_id, String name)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("name", name);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryIdNameOrderByInputdate(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByEventScheduleCount(int group_category_id, String name)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("name", name);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryIdNameOrderByEventScheduleCount(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByEventCount(int group_category_id, String name)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("name", name);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryIdNameOrderByEventCount(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public ArrayList<Group> selectGroupByGroupCategoryIdNameOrderByGroupAttendanceCount(int group_category_id, String name)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("name", name);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryIdNameOrderByGroupAttendanceCount(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public int insertGroup
	(
			int group_category_id,
			String user_id,
			String name,
			String content,
			String region,
			String group_logo,
			String group_image
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("user_id", user_id);
		map.put("name", name);
		map.put("content", content);
		map.put("region", region);
		map.put("group_logo", group_logo);
		map.put("group_image", group_image);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			result = mapper.insertGroup(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<Integer> getGroupIdByName(String name)
	{
		ArrayList<Integer> group_id_list = null;
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByName(name);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
	public ArrayList<Group> selectGroupByName(String name)
	{
		ArrayList<Group> group_list = null;
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByName(name);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public ArrayList<Group> selectGroupByGroupIdListNotGroupId(ArrayList<Integer> group_id_list, int group_id)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_id_list", group_id_list);
		map.put("group_id", group_id);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupIdListNotGroupId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public ArrayList<Group> selectGroupByGroupCategoryIdName(int group_category_id, String name)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("name", name);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryIdName(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public ArrayList<Group> selectGroupByGroupCategoryId(int group_category_id)
	{
		ArrayList<Group> group_list = null;
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryId(group_category_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
	public Group selectGroupByGroupId(int group_id)
	{
		Group group = null;
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group = mapper.selectGroupByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group;
	}
	public ArrayList<Group> selectGroupByGroupIdList(ArrayList<Integer> group_id_list)
	{
		ArrayList<Group> group_list = null;
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupIdList(group_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
}

