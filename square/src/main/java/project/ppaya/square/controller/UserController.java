package project.ppaya.square.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

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

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.shdao.SH_DAO_User;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.EventScheduleVideo;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.Hashtag;
import project.ppaya.square.vo.Reference;
import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;
import project.ppaya.square.yhdao.YHImageAlbumDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleAttendanceDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageFaceDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoDAO;
import project.ppaya.square.yhdao.YHEventScheduleVideoFaceDAO;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhdao.YHUserDAO;
import project.ppaya.square.yhdao.YHVideoAlbumDAO;
import project.ppaya.square.yhutil.YHFileUtil;
import project.ppaya.square.yhutil.YHMSFaceUtil;
import project.ppaya.square.yhutil.YHVideoIndexerUtil;

@Repository
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	@Autowired
	YHEventScheduleImageFaceDAO yh_event_schedule_image_faceDAO;
	@Autowired
	YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO; 
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	
	@Autowired
	SH_DAO_User sh_udao;
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@RequestMapping(value = "joinForm", method = RequestMethod.GET)
	public String joinForm()
	{
		logger.info("join-get입니다!");

		return "member/joinForm";
	}
	
	@RequestMapping(value = "joinForm", method = RequestMethod.POST)
	public String joinForm2(User user)//나중에 email 도메인 부분도 받기
	{
		logger.info("join-post입니다!");
		int check = 0;
		//가입 정보 DB 넣기. 나중에 아이디 부분이랑 도메인 부분이랑 합쳐서 다시 아이디에 넣기. 성공하면 1, 실패하면 0
		check = sh_udao.inputUser(user);
		
		//로그인 실패
		if(check == 0){
			return "member/joinForm";
		}
		//로그인 성공
		else{
			return "main";
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm()
	{
		logger.info("로그인입니다!");

		return "member/loginForm";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String sh_loginCheck(
			HttpSession session
			, Model model
			, String email
			, String password ){
		logger.debug("지나감");
		email = "id1";
		int check = sh_udao.loginCheck(email, password);
		if (check == 1) {
			session.setAttribute("login_email", email);
		} else{
			model.addAttribute("login_check","fail");
			
		}
	
	return "main";
}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String sh_myPageForm(Model request){
		//그 사람이 참여 중인 그룹 리스트 보내기
		String userid = "id1"; //세현: 나중에는 세션에서 id 받아서 넣기. 일단 임시로 넣어 둠
		ArrayList<Group> glist = sh_gdao.getGroupByUser(userid);
		request.addAttribute("glist",glist);
		

		//해시태그 보내기
		/*	
		나중에 table_h 와 table_uh 에 튜블 많이 생성된 후에 살리기 . 일단 아래는 임시
 		ArrayList<Hashtag> hlist = null;
		hlist = sh_udao.getUserHashtag(userid);
 */			
	
		ArrayList<Hashtag> hlist = new ArrayList<Hashtag>();
		int i = 0;
		for(i = 1 ; i <= 6 ; i = i + 1){
			hlist.add( new Hashtag(i, "temphashtag"+i) );
		}
	
		request.addAttribute("hlist",hlist);

		
		return "member/myPageForm";
	}
	
	@RequestMapping(value = "memberPhoto", method = RequestMethod.GET)
	public String memberPhotoForm(Model request)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;	

		String user_id = "id1";
		User user = yh_userDAO.selectUserByUserId(user_id);;
		String image_id = user.getImage_id();
		
		ArrayList<Integer> group_id_list = yh_group_attendanceDAO.getGroupIdByUserId(user_id);
		
		ArrayList<Group> group_list = yh_groupDAO.selectGroupByGroupIdList(group_id_list);		
		request.addAttribute("group_list", group_list);
		
		//EventScheduleImageFace 업데이트
		ArrayList<Integer> event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				yh_event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id(), (new Date()).getTime());
				
				result = YHMSFaceUtil.detectFace(Reference.file_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
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
				
				yh_event_schedule_image_faceDAO.deleteEventScheduleImageFaceByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id());
				
				result = YHMSFaceUtil.detectFace(Reference.file_path, event_schedule_image_list.get(i).getEvent_schedule_image_id());
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
		}
		//EventScheduleImageFace 업데이트 끝

		//ImageAlbum 업데이트
		ArrayList<Integer> attend_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_image_id_list = yh_event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(attend_event_schedule_id_list);

		yh_image_albumDAO.deleteImageAlbumByNotEventScheduleImageIdUserId(attend_event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_image_id_list.size(); i++)
		{
			yh_image_albumDAO.insertImageAlbum(attend_event_schedule_image_id_list.get(i), user_id);
		}
		
		yh_image_albumDAO.updateSelfByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_image_face_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(attend_event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_image_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.file_path, image_id))).getJSONObject(0).getString("faceId"));
		
		ArrayList<String> similar_event_schedule_image_id_list = yh_event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id);
		
		yh_image_albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
		//ImageAlbum 업데이트 끝
		
		//EventScheduleVideoFace 업데이트
		event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_video_id_list.size(); i++)
		{
			String event_schedule_video_id = event_schedule_video_id_list.get(i);
			
			EventScheduleVideo event_schedule_video = yh_event_schedule_videoDAO.selectEventScheduleVideoByEventScheduleVideoId(event_schedule_video_id);
			
			if(event_schedule_video.getDetect_date() == null)
			{
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_id);
				
				jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.file_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.file_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_video.getDetect_date())
			{	
				yh_event_schedule_videoDAO.updateDetectDateByEventScheduleVideoId((new Date()).getTime(), event_schedule_video_id);
				
				yh_event_schedule_video_faceDAO.deleteEventScheduleVideoFaceByEventScheduleVideoId(event_schedule_video_id);
				
				jsonObject = new JSONObject(YHVideoIndexerUtil.getVideoIndex(event_schedule_video_id));
				
				if(jsonObject.isNull("errorType"))
				{
					jsonArray = jsonObject.getJSONObject("summarizedInsights").getJSONArray("faces");
					
					for(int j = 0; j < jsonArray.length(); j++)
					{
						String event_schedule_video_image_id = YHFileUtil.saveJpegFromBase64(YHVideoIndexerUtil.getThumbnail(event_schedule_video_id, jsonArray.getJSONObject(j).getString("thumbnailId")), Reference.file_path);
						
						yh_event_schedule_video_faceDAO.insertEventScheduleVideoFace(((new JSONArray(YHMSFaceUtil.detectFace(Reference.file_path, event_schedule_video_image_id))).getJSONObject(0)).getString("faceId"), event_schedule_video_image_id, event_schedule_video_id);
					}
				}		
			}
		}
		//EventScheduleVideoFace 업데이트 끝
		
		//VideoAlbum 업데이트
		attend_event_schedule_id_list = yh_event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> attend_event_schedule_video_id_list = yh_event_schedule_videoDAO.getEventScheduleVideoIdByEventScheduleIdList(attend_event_schedule_id_list);
		
		yh_video_albumDAO.deleteVideoAlbumByNotEventScheduleVideoIdUserId(attend_event_schedule_video_id_list, user_id);
		
		for(int i = 0; i < attend_event_schedule_video_id_list.size(); i++)
		{
			yh_video_albumDAO.insertVideoAlbum(attend_event_schedule_video_id_list.get(i), user_id);
		}
		
		yh_video_albumDAO.updateSelfByUserId(user_id);
		
		for(int i = 0; i < attend_event_schedule_video_id_list.size(); i++)
		{
			ArrayList<String> attend_event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(attend_event_schedule_video_id_list.get(i));
			
			ArrayList<String> similar_event_schedule_video_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(attend_event_schedule_video_face_id_list, (new JSONArray(YHMSFaceUtil.detectFace(Reference.file_path, image_id))).getJSONObject(0).getString("faceId"));
			
			if(similar_event_schedule_video_face_id.size() != 0)
			{
				yh_video_albumDAO.updateSelfByEventScheduleVideoIdUserId(attend_event_schedule_video_id_list.get(i), user_id);
			}
		}
		//VideoAlbum 업데이트 끝
		
		return "member/memberPhotoForm";
	}
	
}
