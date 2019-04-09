package project.ppaya.square.vo;

public class EventScheduleImage
{
	String event_schedule_image_id;
	String user_id;
	int event_schedule_id;
	String filename;
	String ext;
	Long detect_date;
	String region;
	String latitude;
	String longitude;
	public EventScheduleImage(){}
	public String getEvent_schedule_image_id() {
		return event_schedule_image_id;
	}
	public void setEvent_schedule_image_id(String event_schedule_image_id) {
		this.event_schedule_image_id = event_schedule_image_id;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
