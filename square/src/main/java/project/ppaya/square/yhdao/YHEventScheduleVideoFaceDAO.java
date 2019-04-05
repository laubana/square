package project.ppaya.square.yhdao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.yhmapper.YHEventScheduleImageFaceMapper;
import project.ppaya.square.yhmapper.YHEventScheduleVideoFaceMapper;
import project.ppaya.square.yhmapper.YHEventScheduleVideoMapper;

@Repository
public class YHEventScheduleVideoFaceDAO
{
	@Autowired
	SqlSession sqlSession;

	public int insertEventScheduleVideoFace
	(
			String event_schedule_video_face_id,
			String event_schedule_video_image_id,
			String event_schedule_video_id,
			long detect_date
			)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("event_schedule_video_face_id", event_schedule_video_face_id);
		map.put("event_schedule_video_image_id", event_schedule_video_image_id);
		map.put("event_schedule_video_id", event_schedule_video_id);
		map.put("detect_date", detect_date);
		
		YHEventScheduleVideoFaceMapper mapper = sqlSession.getMapper(YHEventScheduleVideoFaceMapper.class);
		
		try
		{
			result = mapper.insertEventScheduleVideoFace(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}
