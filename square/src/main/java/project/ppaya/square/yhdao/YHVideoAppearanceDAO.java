package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHVideoAppearanceDAO
{
	@Autowired
	SqlSession sqlSession;

	public ArrayList<VideoAppearance> selectVideoAppearanceByFaceIdList(ArrayList<String> face_id_list)
	{
		ArrayList<VideoAppearance> video_appearance_list = null;
		
		YHVideoAppearanceMapper mapper = sqlSession.getMapper(YHVideoAppearanceMapper.class);
		
		try
		{
			video_appearance_list = mapper.selectVideoAppearanceByFaceIdList(face_id_list);
		}
		catch(Exception error){error.printStackTrace();}
		
		return video_appearance_list;
	}
	public int insertVideoAppearance(String face_id, long start_time, long end_time)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("face_id", face_id);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		
		YHVideoAppearanceMapper mapper = sqlSession.getMapper(YHVideoAppearanceMapper.class);
		
		try
		{
			result = mapper.insertVideoAppearance(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
}