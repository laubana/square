package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public int deleteGroupCommentByGroupCommentIdUserId(int group_comment_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_comment_id", group_comment_id);
		map.put("user_id", user_id);
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);	
		
		try
		{
			result = mapper.deleteGroupCommentByGroupCommentIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateContentByGroupCommentIdUserId(int group_comment_id, String user_id, String content)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_comment_id", group_comment_id);
		map.put("user_id", user_id);
		map.put("content", content);
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);	
		
		try
		{
			result = mapper.updateContentByGroupCommentIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertGroupComment(int group_id, String user_id, String content)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_id", group_id);
		map.put("user_id", user_id);
		map.put("content", content);
		
		YHGroupCommentMapper mapper = sqlSession.getMapper(YHGroupCommentMapper.class);	
		
		try
		{
			result = mapper.insertGroupComment(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
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

