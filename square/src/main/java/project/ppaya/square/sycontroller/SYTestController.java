package project.ppaya.square.sycontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SYTestController
{ 
	private static final Logger logger = LoggerFactory.getLogger(SYTestController.class);
	
	@RequestMapping(value = "test1", method = RequestMethod.GET)
	public String test1()
	{
		return "sytest/test1";
	}
}
