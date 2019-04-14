package project.ppaya.square.vo;

public class EventScheduleCommentTag
{
	int event_schedule_comment_t_id;
	int event_schedule_comment_id;
	String tag;
	long input_date;
	public EventScheduleCommentTag(){}
	public int getEvent_schedule_comment_t_id() {
		return event_schedule_comment_t_id;
	}
	public void setEvent_schedule_comment_t_id(int event_schedule_comment_t_id) {
		this.event_schedule_comment_t_id = event_schedule_comment_t_id;
	}
	public int getEvent_schedule_comment_id() {
		return event_schedule_comment_id;
	}
	public void setEvent_schedule_comment_id(int event_schedule_comment_id) {
		this.event_schedule_comment_id = event_schedule_comment_id;
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
