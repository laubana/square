package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

public interface YHVideoAlbumMapper
{
	public int getBlindByUserIdEventScheduleVideoId(HashMap<String, Object> map);
	public ArrayList<String> getEventScheduleVideoIdByUserIdNotBlind(String user_id);
	public int updateBlindByUserIdEventScheduleVideoId(HashMap<String, Object> map);
	public int insertVideoAlbum(HashMap<String, Object> map);
	public int deleteVideoAlbumByNotEventScheduleVideoIdUserId(HashMap<String, Object> map);
	public int updateSelfByUserId(String user_id);
	public int updateSelfByEventScheduleVideoIdUserId(HashMap<String, Object> map);
	public ArrayList<String> getEventScheduleVideoIdByEventScheduleVideoIdListUserIdSelf(HashMap<String, Object> map);
}
