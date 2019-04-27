package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.EventScheduleComment;

public interface YHEventScheduleCommentMapper
{
	public int deleteEventScheduleCommentByEventScheduleCommentIdUserId(HashMap<String, Object> map);
	public int updateContentByEventScheduleCommentIdUserId(HashMap<String, Object> map);
	public EventScheduleComment selectEventScheduleCommentByEventScheduleCommentId(int event_schedule_comment_id);
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleId(int event_schedule_id);
}
