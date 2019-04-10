package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupCategory;

public interface YHGroupCategoryMapper
{
	public GroupCategory selectGroupCategoryByGroupCategoryId(int group_category_id);
	public ArrayList<GroupCategory> selectGroupCategory();
}
