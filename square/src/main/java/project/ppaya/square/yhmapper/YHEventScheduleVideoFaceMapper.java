package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.ImageAlbum;
import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.vo.EventScheduleVideoFace;

public interface YHEventScheduleVideoFaceMapper
{
	public int deleteEventScheduleVideoFaceByEventScheduleVideoId(String event_schedule_video_id);
	public int insertEventScheduleVideoFace(HashMap<String, Object> map);
	public int updateEventScheduleVideoFaceIdByEventScheduleVideoImageId(HashMap<String, Object> map);
	public ArrayList<EventScheduleVideoFace> selectEventScheduleVideoFaceByEventScheduleVideoId(String event_schedule_video_id);
	public ArrayList<String> getEventScheduleVideoFaceIdByEventScheduleVideoId(String event_schdule_video_id);
	public ArrayList<String> getEventScheduleVideoFaceIdByEventScheduleVideoIdList(ArrayList<String> event_schedule_video_id_list);
}
