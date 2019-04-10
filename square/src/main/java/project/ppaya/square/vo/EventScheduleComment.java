package project.ppaya.square.vo;

public class EventScheduleComment
{
	int event_schedule_comment_id;
	int event_schedule_id;
	String user_id;
	String content;
	User user;
	long input_date;
	public EventScheduleComment(){}
	public int getEvent_schedule_comment_id() {
		return event_schedule_comment_id;
	}
	public void setEvent_schedule_comment_id(int event_schedule_comment_id) {
		this.event_schedule_comment_id = event_schedule_comment_id;
	}
	public int getEvent_schedule_id() {
		return event_schedule_id;
	}
	public void setEvent_schedule_id(int event_schedule_id) {
		this.event_schedule_id = event_schedule_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getInput_date() {
		return input_date;
	}
	public void setInput_date(long input_date) {
		this.input_date = input_date;
	}
}
