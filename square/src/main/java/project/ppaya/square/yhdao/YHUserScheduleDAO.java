package project.ppaya.square.yhdao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class YHUserScheduleDAO
{
	@Autowired
	SqlSession sqlSession;
}

