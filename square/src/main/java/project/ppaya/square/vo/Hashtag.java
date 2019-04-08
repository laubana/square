package project.ppaya.square.vo;

public class Hashtag {

	int hashtag_id;
	String name;
	
	public Hashtag() {
		super();
	}

	public Hashtag(int hashtag_id) {
		super();
		this.hashtag_id = hashtag_id;
	}

	public Hashtag(String name) {
		super();
		this.name = name;
	}

	public Hashtag(int hashtag_id, String name) {
		super();
		this.hashtag_id = hashtag_id;
		this.name = name;
	}

	public int getHashtag_id() {
		return hashtag_id;
	}

	public void setHashtag_id(int hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
