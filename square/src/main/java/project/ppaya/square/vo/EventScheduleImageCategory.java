package project.ppaya.square.vo;

public class EventScheduleImageCategory
{
	int event_schedule_image_c_id;
	String event_schedule_image_id;
	String category;
	long input_date;
	public EventScheduleImageCategory(){}
	public int getEvent_schedule_image_c_id() {
		return event_schedule_image_c_id;
	}
	public void setEvent_schedule_image_c_id(int event_schedule_image_c_id) {
		this.event_schedule_image_c_id = event_schedule_image_c_id;
	}
	public String getEvent_schedule_image_id() {
		return event_schedule_image_id;
	}
	public void setEvent_schedule_image_id(String event_schedule_image_id) {
		this.event_schedule_image_id = event_schedule_image_id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getInput_date() {
		return input_date;
	}
	public void setInput_date(long input_date) {
		this.input_date = input_date;
	}
}
