package project.ppaya.square.vo;

public class EventCommentTag
{
	int event_comment_id;
	String tag;
	long input_date;
	public EventCommentTag(){}
	public int getEvent_comment_id() {
		return event_comment_id;
	}
	public void setEvent_comment_id(int event_comment_id) {
		this.event_comment_id = event_comment_id;
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
