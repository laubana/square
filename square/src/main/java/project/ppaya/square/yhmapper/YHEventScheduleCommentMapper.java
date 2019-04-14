package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.EventScheduleComment;

public interface YHEventScheduleCommentMapper
{
	public ArrayList<EventScheduleComment> selectEventScheduleCommentByEventScheduleId(int event_schedule_id);
}
