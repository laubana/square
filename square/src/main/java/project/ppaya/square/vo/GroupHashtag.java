package project.ppaya.square.vo;

public class GroupHashtag
{
	String hashtag;
	int group_category_id;
	int group_id;
	
	public GroupHashtag(){}
	public GroupHashtag(String hashtag, int group_id) {
		super();
		this.hashtag = hashtag;
		this.group_id = group_id;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getGroup_category_id() {
		return group_category_id;
	}
	public void setGroup_category_id(int group_category_id) {
		this.group_category_id = group_category_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
}
