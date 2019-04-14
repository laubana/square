package project.ppaya.square.vo;

public class EventScheduleImageTag
{
	int event_schedule_image_t_id;
	String event_schedule_image_id;
	String tag;
	long input_date;
	public EventScheduleImageTag(){}
	public int getEvent_schedule_image_t_id() {
		return event_schedule_image_t_id;
	}
	public void setEvent_schedule_image_t_id(int event_schedule_image_t_id) {
		this.event_schedule_image_t_id = event_schedule_image_t_id;
	}
	public String getEvent_schedule_image_id() {
		return event_schedule_image_id;
	}
	public void setEvent_schedule_image_id(String event_schedule_image_id) {
		this.event_schedule_image_id = event_schedule_image_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public long getInput_date() {
		return input_date;
	}
	public void setInput_date(long input_date) {
		this.input_date = input_date;
	}
}
