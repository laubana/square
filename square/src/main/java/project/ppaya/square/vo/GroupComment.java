package project.ppaya.square.vo;

import java.util.ArrayList;

public class GroupComment
{
	int group_comment_id;
	int group_id;
	String user_id;
	String content;
	User user;
	ArrayList<String> group_comment_tag_list;
	long input_date;
	public GroupComment(){}
	public int getGroup_comment_id() {
		return group_comment_id;
	}
	public void setGroup_comment_id(int group_comment_id) {
		this.group_comment_id = group_comment_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
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
	public ArrayList<String> getGroup_comment_tag_list() {
		return group_comment_tag_list;
	}
	public void setGroup_comment_tag_list(ArrayList<String> group_comment_tag_list) {
		this.group_comment_tag_list = group_comment_tag_list;
	}
	public long getInput_date() {
		return input_date;
	}
	public void setInput_date(long input_date) {
		this.input_date = input_date;
	}
}
