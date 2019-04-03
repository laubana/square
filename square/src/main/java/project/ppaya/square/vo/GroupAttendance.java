package project.ppaya.square.vo;

public class GroupAttendance
{
	int group_id;
	String user_id;
	int blind;
	public GroupAttendance(){}
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
	public int getBlind() {
		return blind;
	}
	public void setBlind(int blind) {
		this.blind = blind;
	}
}
