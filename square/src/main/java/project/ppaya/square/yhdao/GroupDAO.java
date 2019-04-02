package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.yhmapper.GroupMapper;
import project.ppaya.square.yhvo.Group;

@Repository
public class GroupDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<Group> selectGroupByGroupIdList(ArrayList<Integer> group_id_list)
	{
		ArrayList<Group> group_list = null;
		
		GroupMapper mapper = sqlSession.getMapper(GroupMapper.class);
		
		try
		{
			group_list = mapper.selectGroupByGroupIdList(group_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_list;
	}
}

