package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.ImageAlbum;

public interface YHImageAlbumMapper
{
	public int insertImageAlbum(HashMap<String, Object> map);
	public int deleteImageAlbumByNotEventScheduleImageIdUserId(HashMap<String, Object> map);
	public ArrayList<ImageAlbum> selectImageAlbumByEventScheduleImageIdUserId(HashMap<String, Object> map);
	public ArrayList<ImageAlbum> selectImageAlbumByEventScheduleImageIdListUserId(HashMap<String, Object> map);
	public ArrayList<ImageAlbum> selectImageAlbumByEventScheduleImageIdListUserIdSelf(HashMap<String, Object> map);
	public int updateSelfByUserId(String user_id);
	public int updateSelfByEventScheduleImageIdUserId(HashMap<String, Object> map);
	public int updateSelfByEventScheduleImageIdListUserId(HashMap<String, Object> map);
	public ArrayList<String> getEventScheduleImageIdByEventScheduleImageIdListUserIdSelf(HashMap<String, Object> map);
}
