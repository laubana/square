package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleVideoFaceDAO
{
	@Autowired
	SqlSession sqlSession;

	public int deleteEventScheduleVideoFaceByEventScheduleVideoId(String event_schedule_video_id)
	{
		int result = 0;
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			result = mapper.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertEventScheduleVideoFace
	(
			String event_schedule_video_face_id,
			String event_schedule_video_image_id,
			String event_schedule_video_id
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_face_id", event_schedule_video_face_id);
		map.put("event_schedule_video_image_id", event_schedule_video_image_id);
		map.put("event_schedule_video_id", event_schedule_video_id);
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleVideoFace(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateEventScheduleVideoFaceIdByEventScheduleVideoImageId
	(
			String event_schedule_video_face_id,
			String event_schedule_video_image_id
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_face_id", event_schedule_video_face_id);
		map.put("event_schedule_video_image_id", event_schedule_video_image_id);
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			result = mapper.updateEventScheduleVideoFaceIdByEventScheduleVideoImageId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<EventScheduleVideoFace> selectEventScheduleVideoFaceByEventScheduleVideoId(String event_schedule_video_id)
	{
		ArrayList<EventScheduleVideoFace> event_schedule_video_face_list = null;
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			event_schedule_video_face_list = mapper.selectEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_face_list;
	}
	public ArrayList<String> getEventScheduleVideoFaceIdByEventScheduleVideoId(String event_schedule_video_id)
	{
		ArrayList<String> event_schedule_video_face_id_list = null;
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			event_schedule_video_face_id_list = mapper.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_face_id_list;
	}
	public ArrayList<String> getEventScheduleVideoFaceIdByEventScheduleVideoIdList(ArrayList<String> event_schedule_video_id_list)
	{
		ArrayList<String> event_schedule_video_face_id_list = null;
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			event_schedule_video_face_id_list = mapper.getEventScheduleVideoFaceIdByEventScheduleVideoIdList(event_schedule_video_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_face_id_list;
	}
}
