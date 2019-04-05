package project.ppaya.square.yhutil;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventSchedule;
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

@Repository
public class YHUserFormUtil
{
	@Autowired
	YHUserDAO userDAO;
	@Autowired
	YHEventDAO eventDAO;
	@Autowired
	YHEventScheduleAttendanceDAO event_schedule_attendanceDAO;
	@Autowired
	YHEventScheduleDAO event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageFaceDAO event_schedule_image_faceDAO;
	@Autowired
	YHGroupAttendanceDAO group_attendanceDAO;
	@Autowired
	YHGroupDAO groupDAO;
	@Autowired
	YHAlbumDAO albumDAO;
	
	public void updateAlbumByUserId(String user_id)
	{
		User user = userDAO.selectUserByUserId(user_id);
		String image_id = user.getImage_id();
		
		ArrayList<Integer> event_schedule_id_list = event_schedule_attendanceDAO.getEventScheduleIdByUserId(user_id);
		
		ArrayList<String> event_schedule_image_id_list = event_schedule_imageDAO.getEventScheduleImageIdByEventScheduleIdList(event_schedule_id_list);

		albumDAO.deleteAlbumByNotEventScheduleImageIdUserId(event_schedule_image_id_list, user_id);
		
		for(int i = 0; i < event_schedule_image_id_list.size(); i++)
		{
			albumDAO.insertAlbum(event_schedule_image_id_list.get(i), user_id);
		}
		
		albumDAO.updateSelfByUserId(user_id);
		
		ArrayList<String> event_schedule_image_face_id_list = event_schedule_image_faceDAO.getEventScheduleImageFaceIdByEventScheduleImageIdList(event_schedule_image_id_list);
		
		ArrayList<String> similar_event_schedule_image_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdListByFaceId(event_schedule_image_face_id_list, YHMSFaceUtil.detectFace(Reference.file_path, image_id));
		
		ArrayList<String> similar_event_schedule_image_id_list = event_schedule_image_faceDAO.getEventScheduleImageIdByEventScheduleImageFaceIdList(similar_event_schedule_image_face_id);
		
		albumDAO.updateSelfByEventScheduleImageIdListUserId(similar_event_schedule_image_id_list, user_id);
	}
	public ArrayList<Group> getGroupByUserId(String user_id)
	{
		ArrayList<Integer> group_id_list = group_attendanceDAO.getGroupIdByUserId(user_id);
		
		return groupDAO.selectGroupByGroupIdList(group_id_list); 
	}
	public ArrayList<Integer> getGroupIdByUserId(String user_id)
	{
		return group_attendanceDAO.getGroupIdByUserId(user_id);
	}
	public void updateEventScheduleImageFaceByGroupIdList(ArrayList<Integer> group_id_list)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<Event> event_list = eventDAO.selectEventByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_id_list = new ArrayList<>();
		for(int i = 0; i < event_list.size(); i++)
		{
			event_id_list.add(event_list.get(i).getEvent_id());
		}
		
		ArrayList<EventSchedule> event_schedule_list = event_scheduleDAO.selectEventScheduleByEventIdList(event_id_list);
		
		ArrayList<Integer> event_schedule_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_list.size(); i++)
		{
			event_schedule_id_list.add(event_schedule_list.get(i).getEvent_schedule_id());
		}
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = YHMSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = YHMSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
}
