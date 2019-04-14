package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHGroupCommentTagDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<GroupCommentTag> selectGroupCommentTagByGroupCommentId(int group_comment_id)
	{
		ArrayList<GroupCommentTag> group_comment_tag_list = null;
		
		YHGroupCommentTagMapper mapper = sqlSession.getMapper(YHGroupCommentTagMapper.class);
		
		try
		{
			group_comment_tag_list = mapper.selectGroupCommentTagByGroupCommentId(group_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment_tag_list;
	}
}

