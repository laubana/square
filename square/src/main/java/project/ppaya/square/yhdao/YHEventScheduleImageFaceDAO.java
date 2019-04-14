package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHEventScheduleImageFaceDAO
{
	@Autowired
	SqlSession sqlSession;

	public int deleteEventScheduleImageFaceByEventScheduleImageId(String event_schedule_image_id)
	{
		int result = 0;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			result = mapper.deleteEventScheduleImageFaceByEventScheduleImageId(event_schedule_image_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public ArrayList<EventScheduleImageFace> selectEventScheduleImageFaceByEventScheduleImageId(String event_schedule_image_id)
	{
		ArrayList<EventScheduleImageFace> event_schedule_image_face_list = null;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			event_schedule_image_face_list = mapper.selectEventScheduleImageFaceByEventScheduleImageId(event_schedule_image_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_face_list;
	}
	public ArrayList<EventScheduleImageFace> selectEventScheduleImageFaceByEventScheduleImageIdList
	(
			ArrayList<String> event_schedule_image_id_list
			)
	{
		ArrayList<EventScheduleImageFace> event_schedule_image_face_list = null;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			event_schedule_image_face_list = mapper.selectEventScheduleImageFaceByEventScheduleImageIdList(event_schedule_image_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_face_list;
	}
	public ArrayList<String> getEventScheduleImageFaceIdByEventScheduleImageId(String event_schedule_image_id)
	{
		ArrayList<String> event_schedule_image_face_id_list = null;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			event_schedule_image_face_id_list = mapper.getEventScheduleImageFaceIdByEventScheduleImageId(event_schedule_image_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_face_id_list;
	}
	public ArrayList<String> getEventScheduleImageFaceIdByEventScheduleImageIdList(ArrayList<String> event_schedule_image_id_list)
	{
		ArrayList<String> event_schedule_image_face_id_list = null;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			event_schedule_image_face_id_list = mapper.getEventScheduleImageFaceIdByEventScheduleImageIdList(event_schedule_image_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_face_id_list;
	}
	public ArrayList<String> getEventScheduleImageIdByEventScheduleImageFaceId(String face_id)
	{
		ArrayList<String> event_schedule_image_id_list = null;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			event_schedule_image_id_list = mapper.getEventScheduleImageIdByEventScheduleImageFaceId(face_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_id_list;
	}
	public ArrayList<String> getEventScheduleImageIdByEventScheduleImageFaceIdList(ArrayList<String> face_id_list)
	{
		ArrayList<String> event_schedule_image_id_list = null;
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			event_schedule_image_id_list = mapper.getEventScheduleImageIdByEventScheduleImageFaceIdList(face_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_image_id_list;
	}
	public int insertEventScheduleImageFace
	(
			String event_schedule_image_face_id,
			String event_schedule_image_id,
			int top,
			int left,
			int width,
			int height
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_face_id", event_schedule_image_face_id);
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("top", top);
		map.put("left", left);
		map.put("width", width);
		map.put("height", height);
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleImageFace(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle
	(
			String event_schedule_image_face_id,
			String event_schedule_image_id,
			int top,
			int left,
			int width,
			int height
			)
	{
		int result = 0;
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_face_id", event_schedule_image_face_id);
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("top", top);
		map.put("left", left);
		map.put("width", width);
		map.put("height", height);
		
		YHEventScheduleImageFaceMapper mapper = sqlSession.getMapper(YHEventScheduleImageFaceMapper.class);
		
		try
		{
			result = mapper.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}
