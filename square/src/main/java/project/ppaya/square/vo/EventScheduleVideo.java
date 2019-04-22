package project.ppaya.square.vo;

public class EventScheduleVideo
{
	String event_schedule_video_id;
	String filename;
	String ext;
	Long detect_date;
	String user_id;
	int group_category_id;
	int group_id;
	int event_id;
	int event_schedule_id;
	String region;
	String latitude;
	String longitude;
	int blind;
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
	public int getGroup_category_id() {
		return group_category_id;
	}
	public void setGroup_category_id(int group_category_id) {
		this.group_category_id = group_category_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getEvent_schedule_id() {
		return event_schedule_id;
	}
	public void setEvent_schedule_id(int event_schedule_id) {
		this.event_schedule_id = event_schedule_id;
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
	public int getBlind() {
		return blind;
	}
	public void setBlind(int blind) {
		this.blind = blind;
	}	
}