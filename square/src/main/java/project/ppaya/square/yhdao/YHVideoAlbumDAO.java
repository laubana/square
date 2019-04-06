package project.ppaya.square.yhdao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.ImageAlbum;
import project.ppaya.square.yhmapper.YHImageAlbumMapper;

@Repository
public class YHVideoAlbumDAO
{
	@Autowired
	SqlSession sqlSession;

}
