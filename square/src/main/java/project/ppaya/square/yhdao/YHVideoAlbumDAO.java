package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHVideoAlbumDAO
{
	@Autowired
	SqlSession sqlSession;

	public ArrayList<String> getEventScheduleVideoIdByUserIdNotBlind(String user_id)
	{
		ArrayList<String> event_schedule_video_id_list = null;
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			event_schedule_video_id_list = mapper.getEventScheduleVideoIdByUserIdNotBlind(user_id);
		}
		catch(Exception error){}
		
		return event_schedule_video_id_list;
	}
	public int getBlindByUserIdEventScheduleVideoId(String user_id, String event_schedule_video_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_schedule_video_id", event_schedule_video_id);
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			result = mapper.getBlindByUserIdEventScheduleVideoId(map);
		}
		catch(Exception error){}
		
		return result;
	}
	public int updateBlindByUserIdEventScheduleVideoId(String user_id, String event_schedule_video_id, int blind)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("event_schedule_video_id", event_schedule_video_id);
		map.put("blind", blind);
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			result = mapper.updateBlindByUserIdEventScheduleVideoId(map);
		}
		catch(Exception error){}
		
		return result;
	}
	public ArrayList<String> getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(ArrayList<String> old_event_schedule_video_id_list, String user_id)
	{
		ArrayList<String> new_event_schedule_video_id_list = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_id_list", old_event_schedule_video_id_list);
		map.put("user_id", user_id);
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			new_event_schedule_video_id_list = mapper.getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(map);
		}
		catch(Exception error){}
		
		return new_event_schedule_video_id_list;
	}
	public int insertVideoAlbum(String event_schedule_video_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_id", event_schedule_video_id);
		map.put("user_id", user_id);
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			result = mapper.insertVideoAlbum(map);
		}
		catch(Exception error){}
		
		return result;
	}
	public int deleteVideoAlbumByNotEventScheduleVideoIdUserId(ArrayList<String> event_schedule_video_id_list, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_id_list", event_schedule_video_id_list);
		map.put("user_id", user_id);
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			result = mapper.deleteVideoAlbumByNotEventScheduleVideoIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;	
	}
	public int updateSelfByUserId(String user_id)
	{
		int result = 0;
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateSelfByEventScheduleVideoIdUserId(String event_schedule_video_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_id", event_schedule_video_id);
		map.put("user_id", user_id);
		
		YHVideoAlbumMapper mapper = sqlSession.getMapper(YHVideoAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByEventScheduleVideoIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}
