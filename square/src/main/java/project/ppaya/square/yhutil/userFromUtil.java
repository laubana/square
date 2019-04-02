package project.ppaya.square.yhutil;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import project.ppaya.square.yhdao.GroupAttendanceDAO;
import project.ppaya.square.yhdao.GroupDAO;
import project.ppaya.square.yhvo.Group;

public class userFromUtil
{
	@Autowired
	static GroupAttendanceDAO group_attendanceDAO;
	@Autowired
	static GroupDAO groupDAO;
	
	public static ArrayList<Group> getGroupByUserId(String user_id)
	{
		ArrayList<Integer> group_id_list = group_attendanceDAO.getGroupIdByUserId(user_id);
		
		return groupDAO.selectGroupByGroupIdList(group_id_list); 
	}
}
