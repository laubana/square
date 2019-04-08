package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.GroupCategory;
import project.ppaya.square.yhmapper.YHGroupCategoryMapper;

@Repository
public class YHGroupCategoryDAO
{
	@Autowired
	SqlSession sqlSession;
	
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

