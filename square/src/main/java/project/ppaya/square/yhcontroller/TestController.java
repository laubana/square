package project.ppaya.square.yhcontroller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.yhdao.GroupAttendanceDAO;
import project.ppaya.square.yhdao.GroupDAO;
import project.ppaya.square.yhutil.userFormUtil;
import project.ppaya.square.yhvo.Group;
import project.ppaya.square.yhvo.GroupAttendance;

/**
 * Handles requests for the application home page.
 */
@Controller
public class TestController
{	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	private userFormUtil userformUtil;
	
	@Autowired
	GroupDAO groupDAO;
	@Autowired
	GroupAttendanceDAO group_attendanceDAO;
	
	@RequestMapping(value = "test_myPage", method = RequestMethod.GET)
	public void test()
	{
		String user_id = "id1";
		ArrayList<Integer> group_id_list = new ArrayList<>();
		group_id_list.add(1);
		ArrayList<GroupAttendance> group_attendance_list = userformUtil.getGroupAttendanceByUserId(user_id);
		ArrayList<Group> group_list = groupDAO.selectGroupByGroupIdList(group_id_list);
		
		/*ArrayList<GroupAttendance> group_attendance_list = userformUtil.getGroupAttendanceByUserId(user_id);
		
		if(group_attendance_list == null)
		{
			System.out.println("NULL입니당");
		}*/
	}
}
