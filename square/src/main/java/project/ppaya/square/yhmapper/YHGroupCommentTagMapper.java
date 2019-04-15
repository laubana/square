package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;

public interface YHGroupCommentTagMapper
{
	public ArrayList<String> getTagByGroupCommentIdList(ArrayList<Integer> group_comment_id_list);
	public int insertGroupCommentTag(HashMap<String, Object> map);
	public ArrayList<String> getTagByGroupCommentId(int group_comment_id);
	public ArrayList<GroupCommentTag> selectGroupCommentTagByGroupCommentId(int group_comment_id);
}
