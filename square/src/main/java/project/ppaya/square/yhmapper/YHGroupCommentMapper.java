package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupComment;

public interface YHGroupCommentMapper
{
	public GroupComment selectGroupCommentByGroupCommentId(int group_comment_id);
	public ArrayList<Integer> getGroupCommentIdByGroupId(int group_id);
	public ArrayList<GroupComment> selectGroupCommentByUserId(String user_id);
	public ArrayList<GroupComment> selectGroupCommentByGroupId(int group_id);
}
