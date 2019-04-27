package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.GroupComment;

public interface YHGroupCommentMapper
{
	public int deleteGroupCommentByGroupCommentIdUserId(HashMap<String, Object> map);
	public int updateContentByGroupCommentIdUserId(HashMap<String, Object> map);
	public int insertGroupComment(HashMap<String, Object> map);
	public GroupComment selectGroupCommentByGroupCommentId(int group_comment_id);
	public ArrayList<Integer> getGroupCommentIdByGroupId(int group_id);
	public ArrayList<GroupComment> selectGroupCommentByUserId(String user_id);
	public ArrayList<GroupComment> selectGroupCommentByGroupId(int group_id);
}
