package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;

public interface YHEventCommentTagMapper
{
	public ArrayList<String> getTagByEventCommentId(int event_comment_id);
	public int insertEventCommentTag(HashMap<String, Object> map);
}
