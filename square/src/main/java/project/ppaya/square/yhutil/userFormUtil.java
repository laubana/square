package project.ppaya.square.yhutil;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.yhdao.GroupAttendanceDAO;
import project.ppaya.square.yhdao.GroupDAO;
import project.ppaya.square.yhvo.Group;
import project.ppaya.square.yhvo.GroupAttendance;

@Repository
public class userFormUtil
{
	@Autowired
	GroupAttendanceDAO group_attendanceDAO;
	@Autowired
	GroupDAO groupDAO;
	
	public ArrayList<GroupAttendance> getGroupAttendanceByUserId(String user_id)
	{
		System.out.println("here");
		
		if(group_attendanceDAO.selectGroupAttendanceByUserId(user_id) == null)
		{
			System.out.println("NULL");		
		}
		return null;//group_attendanceDAO.selectGroupAttendanceByUserId(user_id);
	}
	public ArrayList<Group> getGroupByUserId(String user_id)
	{
		ArrayList<Integer> group_id_list = group_attendanceDAO.getGroupIdByUserId(user_id);
		
		for(int i = 0; i < group_id_list.size(); i++)
		{
			System.out.printf("%d", group_id_list.get(i));
		}
		
		return groupDAO.selectGroupByGroupIdList(group_id_list); 
	}
}
