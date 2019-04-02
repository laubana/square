package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.yhmapper.GroupAttendanceMapper;
import project.ppaya.square.yhvo.GroupAttendance;

@Repository
public class GroupAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<GroupAttendance> selectGroupAttendanceByUserId(String user_id)
	{
		ArrayList<GroupAttendance> group_attendance_list = null;
		
		GroupAttendanceMapper mapper = sqlSession.getMapper(GroupAttendanceMapper.class);
		
		try
		{
			group_attendance_list = mapper.selectGroupAttendanceByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_attendance_list;
	}
	public ArrayList<Integer> getGroupIdByUserId(String user_id)
	{
		ArrayList<Integer> group_id_list = null;
		
		GroupAttendanceMapper mapper = sqlSession.getMapper(GroupAttendanceMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
}

