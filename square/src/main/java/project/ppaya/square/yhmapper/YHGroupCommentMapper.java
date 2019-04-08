package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupComment;

public interface YHGroupCommentMapper
{
	public ArrayList<GroupComment> selectGroupCommentByGroupId(int group_id);
}
