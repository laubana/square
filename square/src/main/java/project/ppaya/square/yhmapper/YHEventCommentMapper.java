package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.EventComment;

public interface YHEventCommentMapper
{
	public ArrayList<EventComment> selectEventCommentByEventId(int event_id);
}
