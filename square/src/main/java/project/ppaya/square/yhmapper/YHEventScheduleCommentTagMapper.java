package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;

public interface YHEventScheduleCommentTagMapper
{
	public ArrayList<String> getTagByEventScheduleCommentId(int event_schedule_comment_id);
	public int insertEventScheduleCommentTag(HashMap<String, Object> map);
}
