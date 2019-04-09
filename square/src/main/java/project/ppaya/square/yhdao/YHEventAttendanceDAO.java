package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.yhmapper.YHEventAttendanceMapper;
import project.ppaya.square.yhmapper.YHGroupAttendanceMapper;

@Repository
public class YHEventAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<String> getUserIdByEventId(int event_id)
	{
		ArrayList<String> user_id_list = null;
		
		YHEventAttendanceMapper mapper = sqlSession.getMapper(YHEventAttendanceMapper.class);
		
		try
		{
			user_id_list = mapper.getUserIdByEventId(event_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_id_list;
	}
}

