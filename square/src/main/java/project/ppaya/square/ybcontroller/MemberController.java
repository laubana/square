package project.ppaya.square.ybcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm() {
		logger.info("로그인입니다!");

		return "member/loginForm";
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPageForm() {
		logger.info("마이페이지입니다!");

		return "member/myPageForm";
	}
	
	@RequestMapping(value = "memberPhoto", method = RequestMethod.GET)
	public String memberPhotoForm() {
		logger.info("개인앨범입니다!");

		return "member/memberPhotoForm";
	}
	
}
