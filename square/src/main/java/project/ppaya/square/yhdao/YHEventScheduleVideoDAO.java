package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.yhmapper.YHEventScheduleImageFaceMapper;
import project.ppaya.square.yhmapper.YHEventScheduleVideoMapper;

@Repository
public class YHEventScheduleVideoDAO
{
	@Autowired
	SqlSession sqlSession;

	public int insertEventScheduleVideo
	(
			String event_schedule_video_id,
			String filename,
			String ext,
			long detect_date,
			String user_id,
			int event_schedule_id
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_id", event_schedule_video_id);
		map.put("filename", filename);
		map.put("ext", ext);
		map.put("detect_date", detect_date);
		map.put("user_id", user_id);
		map.put("event_schedule_id", event_schedule_id);
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleVideo(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int updateDetectDateByEventScheduleVideoId(long detect_date, String event_schedule_video_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("detect_date", detect_date);
		map.put("event_schedule_video_id", event_schedule_video_id);
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			result = mapper.updateDetectDateByEventScheduleVideoId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public EventScheduleVideo selectEventScheduleVideoByEventScheduleVideoId(String event_schedule_video_id)
	{
		EventScheduleVideo event_schedule_video = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video = mapper.selectEventScheduleVideoByEventScheduleVideoId(event_schedule_video_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video;
	}
	public ArrayList<String> getEventScheduleVideoIdByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		ArrayList<String> event_schedule_video_id_list = null;
		
		YHEventScheduleVideoMapper mapper = sqlSession.getMapper(YHEventScheduleVideoMapper.class);
		
		try
		{
			event_schedule_video_id_list = mapper.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return event_schedule_video_id_list;
	}
}