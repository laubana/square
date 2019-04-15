package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;

public interface YHGroupCommentTagMapper
{
	public int insertGroupCommentTag(HashMap<String, Object> map);
	public ArrayList<GroupCommentTag> selectGroupCommentTagByGroupCommentId(int group_comment_id);
}
