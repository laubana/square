package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventComment;

public interface YHEventCommentMapper
{
	public int deleteEventCommentByEventCommentIdUserId(HashMap<String, Object> map);
	public int updateContentByEventCommentIdUserId(HashMap<String, Object> map);
	public EventComment selectEventCommentByEventCommentId(int event_comment_id);
	public ArrayList<EventComment> selectEventCommentByEventId(int event_id);
	public ArrayList<EventComment> selectEventCommentByEventIdList(ArrayList<Integer> event_id_list);
}
