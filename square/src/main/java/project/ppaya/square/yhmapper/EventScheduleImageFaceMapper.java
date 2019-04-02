package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.yhvo.EventScheduleImageFace;

public interface EventScheduleImageFaceMapper
{
	public ArrayList<EventScheduleImageFace> selectEventScheduleImageFaceByEventScheduleImageId(String event_schedule_image_id);
	public ArrayList<EventScheduleImageFace> selectEventScheduleImageFaceByEventScheduleImageIdList(ArrayList<String> event_schedule_image_id_list);
	public ArrayList<String> getEventScheduleImageFaceIdByEventScheduleImageId(String event_schedule_image_id);
	public ArrayList<String> getEventScheduleImageFaceIdByEventScheduleImageIdList(ArrayList<String> event_schedule_image_id_list);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleImageFaceId(String face_id);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleImageFaceIdList(ArrayList<String> face_id_list);
	public int insertEventScheduleImageFace(HashMap<String, Object> map);
	public int updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(HashMap<String, Object> map);
}
