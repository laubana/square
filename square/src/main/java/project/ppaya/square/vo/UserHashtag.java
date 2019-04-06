package project.ppaya.square.vo;

public class UserHashtag {

String user_id;
int hashtag_id;

public UserHashtag() {
	super();
}

public UserHashtag(int hashtag_id) {
	super();
	this.hashtag_id = hashtag_id;
}

public UserHashtag(String user_id) {
	super();
	this.user_id = user_id;
}

public UserHashtag(String user_id, int hashtag_id) {
	super();
	this.user_id = user_id;
	this.hashtag_id = hashtag_id;
}

public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public int getHashtag_id() {
	return hashtag_id;
}

public void setHashtag_id(int hashtag_id) {
	this.hashtag_id = hashtag_id;
}


	
}
