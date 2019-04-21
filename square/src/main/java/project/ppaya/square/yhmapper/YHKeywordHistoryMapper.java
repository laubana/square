package project.ppaya.square.yhmapper;

import java.util.ArrayList;

import project.ppaya.square.vo.*;

public interface YHKeywordHistoryMapper
{
	public ArrayList<String> getKeywordByRank();
	public int insertKeywordHistory(String keyword);
}
