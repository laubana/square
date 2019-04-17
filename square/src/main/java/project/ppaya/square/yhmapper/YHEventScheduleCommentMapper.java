package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.EventScheduleComment;

public interface YHEventScheduleCommentMapper
{
	public EventScheduleComment selectEventScheduleCommentByEventScheduleCommentId(int event_schedule_comment_id);
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list);
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleId(int event_schedule_id);
}
