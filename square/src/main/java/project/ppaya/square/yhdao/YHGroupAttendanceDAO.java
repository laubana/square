package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhmapper.*;

@Repository
public class YHGroupAttendanceDAO
{
	@Autowired
	SqlSession sqlSession;
	
	public int insertGroupAttendance(String user_id, int group_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("group_id", group_id);
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			result = mapper.insertGroupAttendance(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public int deleteGroupAttendanceByGroupIdUserId(String user_id, int group_id)
	{
		int result = 0;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("group_id", group_id);
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			result = mapper.deleteGroupAttendanceByGroupIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return result;
	}
	public GroupAttendance selectGroupAttendanceByGroupIdUserId(String user_id, int group_id)
	{
		GroupAttendance group_attendance = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		map.put("group_id", group_id);
		
		YHGroupAttendanceMapper mapper = sqlSession.getMapper(YHGroupAttendanceMapper.class);
		
		try
		{
			group_attendance = mapper.selectGroupAttendanceByGroupIdUserId(map);
		}
		catch(Exception error){error.printStackTrace();}
		
		return group_attendance;
	}
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

