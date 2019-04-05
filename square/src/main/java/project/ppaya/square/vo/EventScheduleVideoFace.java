package project.ppaya.square.vo;

public class EventScheduleVideoFace
{
	String event_schedule_video_face_id;
	String event_schedule_video_image_id;
	String event_schedule_video_id;
	Long detect_date;
	public EventScheduleVideoFace(){}
	public String getEvent_schedule_video_face_id() {
		return event_schedule_video_face_id;
	}
	public void setEvent_schedule_video_face_id(String event_schedule_video_face_id) {
		this.event_schedule_video_face_id = event_schedule_video_face_id;
	}
	public String getEvent_schedule_video_image_id() {
		return event_schedule_video_image_id;
	}
	public void setEvent_schedule_video_image_id(String event_schedule_video_image_id) {
		this.event_schedule_video_image_id = event_schedule_video_image_id;
	}
	public String getEvent_schedule_video_id() {
		return event_schedule_video_id;
	}
	public void setEvent_schedule_video_id(String event_schedule_video_id) {
		this.event_schedule_video_id = event_schedule_video_id;
	}
	public Long getDetect_date() {
		return detect_date;
	}
	public void setDetect_date(Long detect_date) {
		this.detect_date = detect_date;
	}
}