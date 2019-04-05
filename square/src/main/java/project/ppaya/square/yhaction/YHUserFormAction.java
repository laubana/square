package project.ppaya.square.yhaction;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.ppaya.square.vo.Album;
import project.ppaya.square.yhdao.YHAlbumDAO;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhutil.YHUserFormUtil;

@Repository
@Controller
public class YHUserFormAction
{	
	private static final Logger logger = LoggerFactory.getLogger(YHUserFormAction.class);

	@Autowired
	YHEventDAO eventDAO;
	@Autowired
	YHEventScheduleDAO event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	YHAlbumDAO albumDAO;
}
