package project.ppaya.square.yhdao;

import java.util.ArrayList;

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

