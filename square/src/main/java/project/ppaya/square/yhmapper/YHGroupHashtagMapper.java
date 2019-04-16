package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.GroupHashtag;

public interface YHGroupHashtagMapper
{
	public ArrayList<Integer> getGroupIdByHashtagNotHashtagList(HashMap<String, Object> map);
	public ArrayList<String> getHashtagByGroupId(int group_id);
	public ArrayList<Integer> getGroupIdByHashtag(String hashtag);
	public ArrayList<GroupHashtag> selectGroupHashtagByGroupId(int group_id);
}
