package project.ppaya.square.yhmapper;

import java.util.ArrayList;
import java.util.HashMap;

import project.ppaya.square.vo.*;

public interface YHVideoAppearanceMapper
{
	public ArrayList<VideoAppearance> selectVideoAppearanceByFaceIdList(ArrayList<String> face_id_list);
	public int insertVideoAppearance(HashMap<String, Object> map);
}
