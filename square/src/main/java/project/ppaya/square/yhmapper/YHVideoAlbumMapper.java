package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.ImageAlbum;
import project.ppaya.square.vo.VideoAlbum;

public interface YHVideoAlbumMapper
{
	public int insertVideoAlbum(HashMap<String, Object> map);
	public int deleteVideoAlbumByNotEventScheduleVideoIdUserId(HashMap<String, Object> map);
	public int updateSelfByUserId(String user_id);
	public int updateSelfByEventScheduleVideoIdUserId(HashMap<String, Object> map);
	public ArrayList<String> getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(HashMap<String, Object> map);
}
