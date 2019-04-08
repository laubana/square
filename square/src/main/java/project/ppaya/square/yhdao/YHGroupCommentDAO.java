package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.GroupComment;
import project.ppaya.square.yhmapper.YHGroupCommentMapper;

@Repository
public class YHGroupCommentDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<GroupComment> selectGroupCommentByGroupId(int group_id)
	{
		ArrayList<GroupComment> group_comment_list = null;
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);
		
		try
		{
			group_comment_list = mapper.selectGroupCommentByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment_list;
	}
}

