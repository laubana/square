package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupBoard;

public interface YHGroupBoardMapper
{
	public ArrayList<GroupBoard> selectGroupBoardByGroupId(int group_id);
}
