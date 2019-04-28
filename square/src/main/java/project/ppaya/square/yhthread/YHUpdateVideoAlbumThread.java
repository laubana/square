package project.ppaya.square.yhthread;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

public class YHUpdateVideoAlbumThread extends Thread
{	
	public static HashMap<Integer, HashMap<Integer, Object>> out_map = new HashMap<>();
	
	private YHEventScheduleVideoFaceDAO yh_event_schedule_video_faceDAO;
	private YHVideoAlbumDAO yh_video_albumDAO;
	
	private String event_schedule_video_id;
	private User user;
	private int index;
	private int i;
	
	public YHUpdateVideoAlbumThread(int index, int i, String event_schedule_video_id, User user)
	{
		yh_event_schedule_video_faceDAO = (YHEventScheduleVideoFaceDAO)YHBeanUtil.getBean("YHEventScheduleVideoFaceDAO");
		yh_video_albumDAO = (YHVideoAlbumDAO)YHBeanUtil.getBean("YHVideoAlbumDAO");
		
		this.event_schedule_video_id = event_schedule_video_id;
		this.user = user;
		this.index = index;
		this.i = i;
		out_map.get(index).put(i, false);
	}
	@Override
	public void run()
	{
		ArrayList<String> event_schedule_video_face_id_list = yh_event_schedule_video_faceDAO.getEventScheduleVideoFaceIdByEventScheduleVideoId(event_schedule_video_id);
		
		if(event_schedule_video_face_id_list.size() != 0)
		{
			ArrayList<String> similar_event_schedule_video_face_id = YHMSFaceUtil.getSimilarEventScheduleImageFaceIdByFaceId(event_schedule_video_face_id_list, YHMSFaceUtil.getFaceId(Reference.user_image_path, user.getImage_id()));
			
			if(similar_event_schedule_video_face_id.size() != 0)
			{
				yh_video_albumDAO.updateSelfByEventScheduleVideoIdUserId(event_schedule_video_id, user.getUser_id());
			}
		}
		out_map.get(index).put(this.i, true);
	}
}
