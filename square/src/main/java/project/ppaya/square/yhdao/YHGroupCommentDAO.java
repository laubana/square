package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHGroupCommentDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public GroupComment selectGroupCommentByGroupCommentId(int group_comment_id)
	{
		GroupComment group_comment = null;
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);	
		
		try
		{
			group_comment = mapper.selectGroupCommentByGroupCommentId(group_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment;		
	}
	public ArrayList<Integer> getGroupCommentIdByGroupId(int group_id)
	{
		ArrayList<Integer> group_comment_id_list = null;
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);
		
		try
		{
			group_comment_id_list = mapper.getGroupCommentIdByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment_id_list;
	}
	public ArrayList<GroupComment> selectGroupCommentByUserId(String user_id)
	{
		ArrayList<GroupComment> group_comment_list = null;
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);
		
		try
		{
			group_comment_list = mapper.selectGroupCommentByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment_list;
	}
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

