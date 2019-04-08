package project.ppaya.square.yhdao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.yhmapper.YHGroupAttendanceMapper;

@Repository
public class YHGroupAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<Integer> getGroupIdByUserIdNotBlind(String user_id)
	{
		ArrayList<Integer> group_id_list = null;
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByUserIdNotBlind(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
	public ArrayList<String> getUserIdByGroupId(int group_id)
	{
		ArrayList<String> user_id_list = null;
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			user_id_list = mapper.getUserIdByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return user_id_list;
	}
	public ArrayList<GroupAttendance> selectGroupAttendanceByGroupId(int group_id)
	{
		ArrayList<GroupAttendance> group_attendance_list = null;
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			group_attendance_list = mapper.selectGroupAttendanceByGroupId(group_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_attendance_list;
	}
	public ArrayList<GroupAttendance> selectGroupAttendanceByUserId(String user_id)
	{
		ArrayList<GroupAttendance> group_attendance_list = null;
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
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
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			group_id_list = mapper.getGroupIdByUserId(user_id);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_id_list;
	}
}

