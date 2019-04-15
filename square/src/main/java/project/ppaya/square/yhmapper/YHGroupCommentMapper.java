package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupComment;

public interface YHGroupCommentMapper
{
	public ArrayList<Integer> getGroupCommentIdByGroupId(int group_id);
	public ArrayList<GroupComment> selectGroupCommentByUserId(String user_id);
	public ArrayList<GroupComment> selectGroupCommentByGroupId(int group_id);
}
