package project.ppaya.square.yhaction;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.ppaya.square.vo.ImageAlbum;
import project.ppaya.square.yhdao.YHImageAlbumDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhutil.YHUserFormUtil;

@Repository
@Controller
public class YHUserAction
{	
	private static final Logger logger = LoggerFactory.getLogger(YHUserAction.class);

	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	
	@ResponseBody
	@RequestMapping(value = "/getEventScheduleImageListPOST", method = RequestMethod.POST)
	public void getEventScheduleImageListPOST(HttpSession session, @RequestBody ArrayList<Integer> group_id_list)
	{
		String user_id = "id1";
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		yh_image_albumDAO.selectImageAlbumByEventScheduleImageIdListUserId(event_schedule_image_id_list, user_id);
		yh_image_albumDAO.selectImageAlbumByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id);
	}
}
