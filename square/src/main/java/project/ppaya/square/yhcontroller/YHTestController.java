package project.ppaya.square.yhcontroller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.GroupAttendance;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhutil.YHUserFormUtil;

/**
 * Handles requests for the application home page.
 */
@Repository
@Controller
public class YHTestController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestController.class);
	
	@Autowired
	YHUserFormUtil user_formUtil;
	
	@Autowired
	YHGroupDAO groupDAO;
	@Autowired
	YHGroupAttendanceDAO group_attendanceDAO;
	
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
