package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.Album;

public interface YHAlbumMapper
{
	public int insertAlbum(HashMap<String, Object> map);
	public int deleteAlbumByNotEventScheduleImageIdUserId(HashMap<String, Object> map);
	public ArrayList<Album> selectAlbumByEventScheduleImageIdUserId(HashMap<String, Object> map);
	public ArrayList<Album> selectAlbumByEventScheduleImageIdListUserId(HashMap<String, Object> map);
	public ArrayList<Album> selectAlbumByEventScheduleImageIdListUserIdSelf(HashMap<String, Object> map);
	public int updateSelfByUserId(String user_id);
	public int updateSelfByEventScheduleImageIdUserId(HashMap<String, Object> map);
	public int updateSelfByEventScheduleImageIdListUserId(HashMap<String, Object> map);
}
