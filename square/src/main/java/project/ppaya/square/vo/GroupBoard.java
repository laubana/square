package project.ppaya.square.vo;

public class GroupBoard
{
	int group_board_id;
	int group_id;
	String user_id;
	String title;
	String content;
	public GroupBoard(){}
	public int getGroup_board_id() {
		return group_board_id;
	}
	public void setGroup_board_id(int group_board_id) {
		this.group_board_id = group_board_id;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
