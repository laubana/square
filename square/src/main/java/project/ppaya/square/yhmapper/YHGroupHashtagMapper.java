package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.GroupHashtag;

public interface YHGroupHashtagMapper
{
	public ArrayList<Integer> getGroupIdByHashtag(String hashtag);
	public ArrayList<GroupHashtag> selectGroupHashtagByGroupId(int group_id);
}
