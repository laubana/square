package project.ppaya.square.yhaction;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.ppaya.square.vo.Album;
import project.ppaya.square.yhdao.YHAlbumDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhutil.UserFormUtil;

@Controller
public class UserFormAction
{	
	private static final Logger logger = LoggerFactory.getLogger(UserFormAction.class);
	
	@Autowired
	UserFormUtil user_formUtil;
	@Autowired
	YHEventDAO eventDAO;
	@Autowired
	YHEventScheduleDAO event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	YHAlbumDAO albumDAO;
	
	@ResponseBody
	@RequestMapping(value = "/getAlbumByEventGroupIdList", method = RequestMethod.POST)
	public ArrayList<Album> getAlbumByEventGroupIdListPOST(HttpSession session, ArrayList<Integer> group_id_list)
	{
		String user_id = "id1";
		
		ArrayList<Integer> event_id_list = eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		user_formUtil.updateAlbumByUserId(user_id);
		
		return albumDAO.selectAlbumByEventScheduleImageIdListUserId(event_schedule_image_id_list, user_id);
	}
	@ResponseBody
	@RequestMapping(value = "/getAlbumByEventGroupIdListSelf", method = RequestMethod.POST)
	public ArrayList<Album> getAlbumByEventGroupIdListSelfPOST(HttpSession session, ArrayList<Integer> group_id_list)
	{
		String user_id = "id1";
		
		ArrayList<Integer> event_id_list = eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<String> event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		user_formUtil.updateAlbumByUserId(user_id);
		
		return albumDAO.selectAlbumByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id);
	}
}
