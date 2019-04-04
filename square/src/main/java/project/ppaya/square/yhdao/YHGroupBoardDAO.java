package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.GroupBoard;
import project.ppaya.square.yhmapper.YHGroupBoardMapper;

@Repository
public class YHGroupBoardDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<GroupBoard> selectGroupBoardByGroupId(int group_id)
	{
		ArrayList<GroupBoard> group_board_list = null;
		
		YHGroupBoardMapper mapper = sqlSession.getMapper(YHGroupBoardMapper.class);
		
		try
		{
			group_board_list = mapper.selectGroupBoardByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_board_list;
	}
}

