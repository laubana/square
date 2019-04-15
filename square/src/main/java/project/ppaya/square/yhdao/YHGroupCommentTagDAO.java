package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public ArrayList<String> getTagByGroupCommentIdList(ArrayList<Integer> group_comment_id_list)
	{
		ArrayList<String> group_comment_tag_tag_list = null;
		
		YHGroupCommentTagMapper mapper = sqlSession.getMapper(YHGroupCommentTagMapper.class);
		
		try
		{
			group_comment_tag_tag_list = mapper.getTagByGroupCommentIdList(group_comment_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment_tag_tag_list;
	}
	public int insertGroupCommentTag(int group_comment_id, String tag)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("group_comment_id", group_comment_id);
		map.put("tag", tag);
		
		YHGroupCommentTagMapper mapper = sqlSession.getMapper(YHGroupCommentTagMapper.class);
		
		try
		{
			result = mapper.insertGroupCommentTag(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<String> getTagByGroupCommentId(int group_comment_id)
	{
		ArrayList<String> group_comment_tag_tag_list = null;
		
		YHGroupCommentTagMapper mapper = sqlSession.getMapper(YHGroupCommentTagMapper.class);
		
		try
		{
			group_comment_tag_tag_list = mapper.getTagByGroupCommentId(group_comment_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_comment_tag_tag_list;
	}
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

