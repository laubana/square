package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.Album;
import project.ppaya.square.vo.EventScheduleVideo;

public interface YHEventScheduleVideoMapper
{
	public int insertEventScheduleVideo(HashMap<String, Object> map);
	public int updateDetectDateByEventScheduleVideoId(HashMap<String, Object> map);
	public EventScheduleVideo selectEventScheduleVideoByEventScheduleVideoId(String event_schedule_video_id);
}
