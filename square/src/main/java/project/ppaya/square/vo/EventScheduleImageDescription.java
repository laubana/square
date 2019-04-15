package project.ppaya.square.vo;

public class EventScheduleImageDescription
{
	String event_schedule_image_id;
	String description;
	long input_date;
	public EventScheduleImageDescription(){}
	public String getEvent_schedule_image_id() {
		return event_schedule_image_id;
	}
	public void setEvent_schedule_image_id(String event_schedule_image_id) {
		this.event_schedule_image_id = event_schedule_image_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getInput_date() {
		return input_date;
	}
	public void setInput_date(long input_date) {
		this.input_date = input_date;
	}
}
