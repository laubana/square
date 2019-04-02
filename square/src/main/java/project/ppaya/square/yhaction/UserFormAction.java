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

import project.ppaya.square.yhdao.AlbumDAO;
import project.ppaya.square.yhdao.EventDAO;
import project.ppaya.square.yhdao.EventScheduleDAO;
import project.ppaya.square.yhdao.EventScheduleImageDAO;
import project.ppaya.square.yhutil.UserFormUtil;
import project.ppaya.square.yhvo.Album;

@Controller
public class UserFormAction
{	
	private static final Logger logger = LoggerFactory.getLogger(UserFormAction.class);
	
	@Autowired
	UserFormUtil user_formUtil;
	@Autowired
	EventDAO eventDAO;
	@Autowired
	EventScheduleDAO event_scheduleDAO;
	@Autowired
	EventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	AlbumDAO albumDAO;
	
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
