package project.ppaya.square.controller;

import java.io.File;
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

import project.ppaya.square.vo.Album;
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
import project.ppaya.square.yhutil.YHMSFaceUtil;
import project.ppaya.square.yhutil.YHUserFormUtil;

@Repository
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	YHUserFormUtil yh_user_formUtil;
	@Autowired
	YHMSFaceUtil yh_ms_faceUtil;
	
	@Autowired
	YHUserDAO yh_userDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleAttendanceDAO yh_event_schedule_attendanceDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageFaceDAO yh_event_schedule_image_faceDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO; 
	@Autowired
	YHAlbumDAO yh_albumDAO;
	
	@RequestMapping(value = "joinForm", method = RequestMethod.GET)
	public String joinForm()
	{
		logger.info("로그인입니다!");

		return "member/joinForm";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm()
	{
		logger.info("로그인입니다!");

		return "member/loginForm";
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPageForm(Model request)
	{
		//ArrayList<Group>
		
		return "member/myPageForm";
	}
	
	@RequestMapping(value = "memberPhoto", method = RequestMethod.GET)
	public String memberPhotoForm(Model request)
	{
		long start_time = (new Date()).getTime();
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		User user;
		String user_id = "id1";
		String image_id;
		
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserId(user_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);		
		request.addAttribute("group_list", group_list);
		
		//EventScheduleImageFace 업데이트
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = yh_ms_faceUtil.detectFace(Reference.file_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					yh_event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_list.get(i).getEvent_schedule_image_id(),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = yh_ms_faceUtil.detectFace(Reference.file_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					yh_event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_list.get(i).getEvent_schedule_image_id(),
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
		user = yh_userDAO.selectUserByUserId(user_id);
		image_id = user.getImage_id();
		
		ArrayList<Integer> attend_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(attend_event_schedule_id_list);

		yh_albumDAO.deleteAlbumByNotEventScheduleImageIdUserId(attend_event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_image_id_list.size(); i++)
		{
			yh_albumDAO.insertAlbum(attend_event_schedule_image_id_list.get(i), user_id);
		}
		
		yh_albumDAO.updateSelfByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_image_face_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(attend_event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id = yh_ms_faceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_image_face_id_list, (new JSONArray(yh_ms_faceUtil.detectFace(Reference.file_path, image_id))).getJSONObject(0).getString("faceId")  );
		
		ArrayList<String> similar_event_schedule_image_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id);
		
		yh_albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
		//Album 업데이트 끝
		
		event_id_list = yh_eventDAO.getEventIdByGroupIdList(group_id_list);
		
		event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		
		request.addAttribute("event_schedule_image_list", yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list));
		
		ArrayList<String> old_event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> new_event_schedule_image_id_list = yh_albumDAO.getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(old_event_schedule_image_id_list, user_id);
		
		request.addAttribute("self_event_schedule_image_list", yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleImageIdList(new_event_schedule_image_id_list));
		
		long end_time = (new Date()).getTime();
		
		System.out.println((long)(end_time - start_time) / (long)1000);
		
		return "member/memberPhotoForm";
	}
	
}