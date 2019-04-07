package project.ppaya.square.vo;

public class EventScheduleUserSchedule
{
	int user_schedule_id;
	String user_id;
	int event_schedule_id;
	long start_date;
	long end_date;
	int typeof;
	public EventScheduleUserSchedule(){}
	public EventScheduleUserSchedule(int user_schedule_id, String user_id, int event_schedule_id, long start_date,
			long end_date, int typeof) {
		super();
		this.user_schedule_id = user_schedule_id;
		this.user_id = user_id;
		this.event_schedule_id = event_schedule_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.typeof = typeof;
	}
	public int getUser_schedule_id() {
		return user_schedule_id;
	}
	public void setUser_schedule_id(int user_schedule_id) {
		this.user_schedule_id = user_schedule_id;
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
	public int getTypeof() {
		return typeof;
	}
	public void setTypeof(int typeof) {
		this.typeof = typeof;
	}
}
