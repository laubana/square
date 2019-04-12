package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.Group;
import project.ppaya.square.yhmapper.YHGroupMapper;

@Repository
public class YHGroupDAO
{
	@Autowired
	SqlSession sqlSession;
	
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
	public ArrayList<Group> selectGroupByGroupCategoryIdKeyword(int group_category_id, String keyword)
	{
		ArrayList<Group> group_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_category_id", group_category_id);
		map.put("keyword", keyword);
		
		YHGroupMapper mapper = sqlSession.getMapper(YHGroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupCategoryIdKeyword(map);
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

