package project.ppaya.square.vo;

import java.util.ArrayList;

public class GroupCommentTag
{
	int group_comment_t_id;
	int group_comment_id;
	String tag;
	long input_date;
	public GroupCommentTag(){}
	public int getGroup_comment_t_id() {
		return group_comment_t_id;
	}
	public void setGroup_comment_t_id(int group_comment_t_id) {
		this.group_comment_t_id = group_comment_t_id;
	}
	public int getGroup_comment_id() {
		return group_comment_id;
	}
	public void setGroup_comment_id(int group_comment_id) {
		this.group_comment_id = group_comment_id;
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
