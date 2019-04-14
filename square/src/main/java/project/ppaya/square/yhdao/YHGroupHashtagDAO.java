package project.ppaya.square.yhdao;

import java.util.ArrayList;

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

