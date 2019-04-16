package project.ppaya.square.vo;

public class Group
{
	int group_id;
	int group_category_id;
	String region;
	String user_id;
	String name;
	String content;
	String group_logo;
	String group_image;
	int blind;
	public Group(){}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_category_id() {
		return group_category_id;
	}
	public void setGroup_category_id(int group_category_id) {
		this.group_category_id = group_category_id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGroup_logo() {
		return group_logo;
	}
	public void setGroup_logo(String group_logo) {
		this.group_logo = group_logo;
	}
	public String getGroup_image() {
		return group_image;
	}
	public void setGroup_image(String group_image) {
		this.group_image = group_image;
	}
	public int getBlind() {
		return blind;
	}
	public void setBlind(int blind) {
		this.blind = blind;
	}
}
