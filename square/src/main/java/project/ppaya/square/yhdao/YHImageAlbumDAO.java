package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.ImageAlbum;
import project.ppaya.square.yhmapper.YHImageAlbumMapper;

@Repository
public class YHImageAlbumDAO
{
	@Autowired
	SqlSession sqlSession;

	public int updateSelfByEventScheduleImageIdListUserId(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);
		
		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByEventScheduleImageIdListUserId(map);
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
		
		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
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
		
		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			result = mapper.updateSelfByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int insertImageAlbum(String event_schedule_image_id, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("user_id", user_id);
		
		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			result = mapper.insertImageAlbum(map);
		}
		catch(Exception error){}
		
		return result;
	}
	public int deleteImageAlbumByNotEventScheduleImageIdUserId(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);
		
		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			result = mapper.deleteImageAlbumByNotEventScheduleImageIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;	
	}
	public ArrayList<ImageAlbum> selectImageAlbumByEventScheduleImageIdUserId(String event_schedule_image_id, String user_id)
	{
		ArrayList<ImageAlbum> album_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id", event_schedule_image_id);
		map.put("user_id", user_id);
		
		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			album_list = mapper.selectImageAlbumByEventScheduleImageIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return album_list;	
	}
	public ArrayList<ImageAlbum> selectImageAlbumByEventScheduleImageIdListUserId(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		ArrayList<ImageAlbum> album_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);

		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			album_list = mapper.selectImageAlbumByEventScheduleImageIdListUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return album_list;	
	}
	public ArrayList<ImageAlbum> selectImageAlbumByEventScheduleImageIdListUserIdSelf(ArrayList<String> event_schedule_image_id_list, String user_id)
	{
		ArrayList<ImageAlbum> album_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", event_schedule_image_id_list);
		map.put("user_id", user_id);

		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			album_list = mapper.selectImageAlbumByEventScheduleImageIdListUserIdSelf(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return album_list;	
	}
	public ArrayList<String> getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(ArrayList<String> old_event_schedule_image_id_list, String user_id)
	{
		ArrayList<String> new_event_schedule_image_id_list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_image_id_list", old_event_schedule_image_id_list);
		map.put("user_id", user_id);

		YHImageAlbumMapper mapper = sqlSession.getMapper(YHImageAlbumMapper.class);
		
		try
		{
			new_event_schedule_image_id_list = mapper.getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return new_event_schedule_image_id_list;
	}
}
