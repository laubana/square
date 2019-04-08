package project.ppaya.square.vo;

public class EventScheduleVideo
{
	String event_schedule_video_id;
	String filename;
	String ext;
	Long detect_date;
	String user_id;
	int event_schedule_id;
	double latitude;
	double longitude;
	public EventScheduleVideo(){}
	public String getEvent_schedule_video_id() {
		return event_schedule_video_id;
	}
	public void setEvent_schedule_video_id(String event_schedule_video_id) {
		this.event_schedule_video_id = event_schedule_video_id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Long getDetect_date() {
		return detect_date;
	}
	public void setDetect_date(Long detect_date) {
		this.detect_date = detect_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getEvent_schedule_id() {
		return event_schedule_id;
	}
	public void setEvent_schedule_id(int event_schedule_id) {
		this.event_schedule_id = event_schedule_id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}