package project.ppaya.square.vo;

public class GroupComment
{
	int group_comment_id;
	int group_id;
	String user_id;
	String content;
	User user;
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
}
