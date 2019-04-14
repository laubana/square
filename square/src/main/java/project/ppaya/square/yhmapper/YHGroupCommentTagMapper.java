package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.*;

public interface YHGroupCommentTagMapper
{
	public ArrayList<GroupCommentTag> selectGroupCommentTagByGroupCommentId(int group_comment_id);
}
