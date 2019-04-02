package project.ppaya.square.ybcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String mainForm() {
		logger.info("메인입니다!");

		return "main/mainForm";
	}
	
}
