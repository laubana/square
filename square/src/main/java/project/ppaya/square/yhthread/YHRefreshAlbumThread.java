package project.ppaya.square.yhthread;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHRefreshAlbumThread extends Thread
{	
	public static HashMap<Integer, HashMap<Integer, Object>> out_map = new HashMap<>();
	
	public static ArrayList<HashMap<String, Object>> video_list = new ArrayList<>();
	
	private YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	private YHVideoAlbumDAO yh_video_albumDAO;
	private YHVideoAppearanceDAO yh_video_appearanceDAO;
	
	private EventScheduleVideo event_schedule_video;
	private User user;
	private int index;
	private int i;
	private String path;
	
	public YHRefreshAlbumThread(int index, int i, EventScheduleVideo event_schedule_video, User user, String path)
	{
		yh_event_schedule_video_faceDAO = (YHEventScheduleVideoFaceDAO)YHBeanUtil.getBean("YHEventScheduleVideoFaceDAO");
		yh_video_albumDAO = (YHVideoAlbumDAO)YHBeanUtil.getBean("YHVideoAlbumDAO");
		yh_video_appearanceDAO = (YHVideoAppearanceDAO)YHBeanUtil.getBean("YHVideoAppearanceDAO"); 
		
		this.event_schedule_video = event_schedule_video;
		this.user = user;
		this.index = index;
		this.i = i;
		this.path = path;
		out_map.get(index).put(i, false);
	}
	@Override
	public void run()
	{
		HashMap<String, Object> video_list_map = new HashMap<>();
		
		video_list_map.put("index", i);
		video_list_map.put("video", event_schedule_video);
		video_list_map.put("blind", yh_video_albumDAO.getBlindByUserIdEventScheduleVideoId(user.getUser_id(), event_schedule_video.getEvent_schedule_video_id()));
		
		ArrayList<String> face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video.getEvent_schedule_video_id());
		
		if(face_id_list.size() != 0)
		{
			ArrayList<String> similar_face_id_list = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(face_id_list, YHMSFaceUtil.getFaceId(path + Reference.user_image_path, user.getImage_id()));
			video_list_map.put("appearance_list", yh_video_appearanceDAO.selectVideoAppearanceByFaceIdList(similar_face_id_list));
		}
		else
		{
			video_list_map.put("appearance_list", new ArrayList<VideoAppearance>());
		}
		
		video_list.add(video_list_map);
		
		out_map.get(index).put(this.i, true);
	}
}
