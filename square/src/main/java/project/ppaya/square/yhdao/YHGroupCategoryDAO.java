package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHGroupCategoryDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public GroupCategory selectGroupCategoryByGroupCategoryId(int group_category_id)
	{
		GroupCategory group_category = null;
		
		YHGroupCategoryMapper mapper = sqlSession.getMapper(YHGroupCategoryMapper.class);
		
		try
		{
			group_category = mapper.selectGroupCategoryByGroupCategoryId(group_category_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_category;
	}
	public ArrayList<GroupCategory> selectGroupCategory()
	{
		ArrayList<GroupCategory> group_category_list = null;
		
		YHGroupCategoryMapper mapper = sqlSession.getMapper(YHGroupCategoryMapper.class);
		
		try
		{
			group_category_list = mapper.selectGroupCategory();
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_category_list;
	}
}

