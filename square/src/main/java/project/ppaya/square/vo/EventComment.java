package project.ppaya.square.vo;

public class EventComment
{
	int event_comment_id;
	int event_id;
	String user_id;
	String content;
	User user;
	long input_date;
	public EventComment(){}
	public int getEvent_comment_id() {
		return event_comment_id;
	}
	public void setEvent_comment_id(int event_comment_id) {
		this.event_comment_id = event_comment_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
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
