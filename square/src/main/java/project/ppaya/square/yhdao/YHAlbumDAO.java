package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.Album;
import project.ppaya.square.yhmapper.YHAlbumMapper;

@Repository
public class YHAlbumDAO
{
	@Autowired
	SqlSession sqlSession;

	public int updateSelfByEventScheduleImageIdListUserId(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);
		
		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByEventScheduleImageIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateSelfByEventScheduleImageIdUserId(String event_schedule_image_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("user_id", user_id);
		
		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByEventScheduleImageIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateSelfByUserId(String user_id)
	{
		int result = 0;
		
		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertAlbum(String event_schedule_image_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("user_id", user_id);
		
		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			result = mapper.insertAlbum(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int deleteAlbumByNotEventScheduleImageIdUserId(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);
		
		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			result = mapper.deleteAlbumByNotEventScheduleImageIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;	
	}
	public ArrayList<Album> selectAlbumByEventScheduleImageIdUserId(String event_schedule_image_id, String user_id)
	{
		ArrayList<Album> album_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("user_id", user_id);
		
		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			album_list = mapper.selectAlbumByEventScheduleImageIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return album_list;	
	}
	public ArrayList<Album> selectAlbumByEventScheduleImageIdListUserId(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		ArrayList<Album> album_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);

		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			album_list = mapper.selectAlbumByEventScheduleImageIdListUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return album_list;	
	}
	public ArrayList<Album> selectAlbumByEventScheduleImageIdListUserIdSelf(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		ArrayList<Album> album_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);

		YHAlbumMapper mapper = sqlSession.getMapper(YHAlbumMapper.class);
		
		try
		{
			album_list = mapper.selectAlbumByEventScheduleImageIdListUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return album_list;	
	}
}
