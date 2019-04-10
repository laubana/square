package project.ppaya.square.vo;

public class EventSchedule
{
	int event_schedule_id;
	int event_id;
	long start_date;
	long end_date;
	String name;
	String content;
	String place;
	public EventSchedule(){}
	public int getEvent_schedule_id() {
		return event_schedule_id;
	}
	public void setEvent_schedule_id(int event_schedule_id) {
		this.event_schedule_id = event_schedule_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public long getStart_date() {
		return start_date;
	}
	public void setStart_date(long start_date) {
		this.start_date = start_date;
	}
	public long getEnd_date() {
		return end_date;
	}
	public void setEnd_date(long end_date) {
		this.end_date = end_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
}
