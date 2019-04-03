package project.ppaya.square.yhcontroller;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventSchedule;
import project.ppaya.square.vo.EventScheduleAttendance;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.Reference;
import project.ppaya.square.vo.User;
import project.ppaya.square.yhdao.YHAlbumDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleAttendanceDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageFaceDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHUserDAO;
import project.ppaya.square.yhutil.MSFaceUtil;
import project.ppaya.square.yhutil.UserFormUtil;

@Repository
@Controller
public class YHUserController {

	private static final Logger logger = LoggerFactory.getLogger(YHUserController.class);

	@Autowired
	UserFormUtil user_formUtil;
	@Autowired
	MSFaceUtil ms_faceUtil;
	
	@Autowired
	YHUserDAO userDAO;
	@Autowired
	YHEventDAO eventDAO;
	@Autowired
	YHEventScheduleDAO event_scheduleDAO;
	@Autowired
	YHEventScheduleAttendanceDAO event_schedule_attendanceDAO;
	@Autowired
	YHEventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageFaceDAO event_schedule_image_faceDAO;
	@Autowired
	YHGroupDAO groupDAO;
	@Autowired
	YHGroupAttendanceDAO group_attendanceDAO; 
	@Autowired
	YHAlbumDAO albumDAO;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm() {
		logger.info("로그인입니다!");

		return "member/loginForm";
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPageForm() {
		logger.info("마이페이지입니다!");
		
		return "member/myPageForm";
	}
	
	@RequestMapping(value = "memberPhoto", method = RequestMethod.GET)
	public String memberPhotoForm(Model model)
	{
		logger.info("개인앨범입니다!");

		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		User user;
		String user_id = "id1";
		String image_id;

		ArrayList<Group> group_list;
		ArrayList<Integer> group_id_list;
		ArrayList<Integer> event_id_list;
		ArrayList<EventSchedule> event_schedule_list;
		ArrayList<Integer> event_schedule_id_list;
		ArrayList<Integer> attend_event_schedule_id_list;
		ArrayList<EventScheduleImage> event_schedule_image_list;
		ArrayList<String> event_schedule_image_id_list;
		ArrayList<String> attend_event_schedule_image_id_list;
		ArrayList<String> attend_event_schedule_image_face_id_list;
		ArrayList<String> similar_event_schedule_image_face_id ;
		ArrayList<String> similar_event_schedule_image_id_list;
		
		group_id_list = group_attendanceDAO.getGroupIdByUserId(user_id);
		
		group_list = groupDAO.selectGroupByGroupIdList(group_id_list);		
		model.addAttribute("group_list", group_list);
		
		//EventScheduleImageFace 업데이트
		event_id_list = eventDAO.getEventIdByGroupIdList(group_id_list);
		
		event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_list.get(i).getEvent_schedule_image_id(),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_list.get(i).getEvent_schedule_image_id(),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
		//EventScheduleImageFace 업데이트 끝
		
		//Album 업데이트
		user = userDAO.selectUserByUserId(user_id);
		image_id = user.getImage_id();
		
		attend_event_schedule_id_list = event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		attend_event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(attend_event_schedule_id_list);

		albumDAO.deleteAlbumByNotEventScheduleImageIdUserId(attend_event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_image_id_list.size(); i++)
		{
			albumDAO.insertAlbum(attend_event_schedule_image_id_list.get(i), user_id);
		}
		
		albumDAO.updateSelfByUserId(user_id);
		
		attend_event_schedule_image_face_id_list = event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(attend_event_schedule_image_id_list);
		
		similar_event_schedule_image_face_id = ms_faceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_image_face_id_list, ms_faceUtil.detectFace(Reference.file_path, image_id));
		for(int i = 0; i < similar_event_schedule_image_face_id.size(); i++)
		{
			logger.debug("{}", similar_event_schedule_image_face_id.get(i));
		}
		
		similar_event_schedule_image_id_list = event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id);
		
		albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
		//Album 업데이트 끝
		
		event_id_list = eventDAO.getEventIdByGroupIdList(group_id_list);
		
		event_schedule_id_list = event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		model.addAttribute("image_list", albumDAO.selectAlbumByEventScheduleImageIdListUserId(event_schedule_image_id_list, user_id));
		//model.addAttribute("self_image_list", albumDAO.selectAlbumByEventScheduleImageIdListUserIdSelf(event_schedule_image_id_list, user_id));
		
		return "member/memberPhotoForm";
	}
	
}
