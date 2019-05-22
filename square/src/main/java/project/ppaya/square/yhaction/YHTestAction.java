package project.ppaya.square.yhaction;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;

@Repository
@Controller
public class YHTestAction
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestAction.class);

	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHImageAlbumDAO yh_image_albumDAO;
	@Autowired
	YHVideoAlbumDAO yh_video_albumDAO;
	@Autowired
	YHEventScheduleVideoDAO yh_event_schedule_videoDAO;
	
	@ResponseBody
	@RequestMapping(value = "/yhtestaction3", method = RequestMethod.POST)
	public void yhtestaction(HttpSession session, @RequestBody HashMap<String, Object> map)
	{
		String test = (String)map.get("test");
		String base64 = (String)map.get("file");
		logger.debug("{}", base64);
		logger.debug("{}", test);
	}	
}
