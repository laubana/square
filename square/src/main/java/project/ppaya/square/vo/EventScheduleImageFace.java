package project.ppaya.square.vo;

public class EventScheduleImageFace
{
	String event_image_face_id;
	String event_image_id;
	int top;
	int lef;
	int width;
	int height;
	long detect_date;
	public EventScheduleImageFace(){}
	public String getEvent_image_face_id() {
		return event_image_face_id;
	}
	public void setEvent_image_face_id(String event_image_face_id) {
		this.event_image_face_id = event_image_face_id;
	}
	public String getEvent_image_id() {
		return event_image_id;
	}
	public void setEvent_image_id(String event_image_id) {
		this.event_image_id = event_image_id;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLef() {
		return lef;
	}
	public void setLef(int lef) {
		this.lef = lef;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public long getDetect_date() {
		return detect_date;
	}
	public void setDetect_date(long detect_date) {
		this.detect_date = detect_date;
	}
	@Override
	public String toString() {
		return "Face [event_image_id=" + event_image_id + ", event_image_face_id=" + event_image_face_id + ", top="
				+ top + ", lef=" + lef + ", width=" + width + ", height=" + height + ", detect_date=" + detect_date
				+ "]";
	}
}
