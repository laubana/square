package project.ppaya.square.ybcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GroupController {

	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@RequestMapping(value = "groupSearch", method = RequestMethod.GET)
	public String searchForm() {
		logger.info("그룹탐색입니다!");

		return "group/groupSearchForm";
	}
	
	@RequestMapping(value = "groupMain", method = RequestMethod.GET)
	public String mainForm() {
		logger.info("그룹탐색입니다!");

		return "group/groupMainForm";
	}
	
	@RequestMapping(value = "groupPhoto", method = RequestMethod.GET)
	public String photoForm() {
		logger.info("그룹탐색입니다!");

		return "group/groupPhotoForm";
	}
}
