package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHGroupHashtagDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<String> getGroupHashtagRank()
	{
		ArrayList<String> hashtag_list = null;
		
		YHGroupHashtagMapper mapper = sqlSession.getMapper(YHGroupHashtagMapper.class);
		
		try
		{
			hashtag_list = mapper.getGroupHashtagRank();
		}
		catch(Exception error){error.printStackTrace();}
		
		return hashtag_list;
	}
	public ArrayList<Integer> getGroupIdByHashtagNotHashtagList(String hashtag, ArrayList<String> hashtag_list)
	{
		ArrayList<Integer> group_id_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("hashtag",hashtag);
		map.put("hashtag_list", hashtag_list);
		
		YHGroupHashtagMapper mapper = sqlSession.getMapper(YHGroupHashtagMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByHashtagNotHashtagList(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
	public ArrayList<String> getHashtagByGroupId(int group_id)
	{
		ArrayList<String> hashtag_list = null;
		
		YHGroupHashtagMapper mapper = sqlSession.getMapper(YHGroupHashtagMapper.class);
		
		try
		{
			hashtag_list = mapper.getHashtagByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return hashtag_list;
	}
	public ArrayList<Integer> getGroupIdByHashtag(String hashtag)
	{
		ArrayList<Integer> group_id_list = null;
		
		YHGroupHashtagMapper mapper = sqlSession.getMapper(YHGroupHashtagMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByHashtag(hashtag);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
	public ArrayList<GroupHashtag> selectGroupHashtagByGroupId(int group_id)
	{
		ArrayList<GroupHashtag> group_hashtag_list = null;
		
		YHGroupHashtagMapper mapper = sqlSession.getMapper(YHGroupHashtagMapper.class);
		
		try
		{
			group_hashtag_list = mapper.selectGroupHashtagByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_hashtag_list;
	}
}

