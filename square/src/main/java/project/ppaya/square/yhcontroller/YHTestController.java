package project.ppaya.square.yhcontroller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.yhdao.GroupAttendanceDAO;
import project.ppaya.square.yhdao.GroupDAO;
import project.ppaya.square.yhutil.UserFormUtil;
import project.ppaya.square.yhvo.Group;
import project.ppaya.square.yhvo.GroupAttendance;

/**
 * Handles requests for the application home page.
 */
@Repository
@Controller
public class YHTestController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestController.class);
	
	@Autowired
	UserFormUtil user_formUtil;
	
	@Autowired
	GroupDAO groupDAO;
	@Autowired
	GroupAttendanceDAO group_attendanceDAO;
	
	@RequestMapping(value = "test_myPage", method = RequestMethod.GET)
	public void test()
	{
		String user_id = "id1";
		
		ArrayList<Group> group_list = user_formUtil.getGroupByUserId(user_id);
		
		for(int i = 0; i < group_list.size(); i++)
		{
			logger.debug("{}", group_list.get(i).getGroup_id());
		}
	}
}
