package project.ppaya.square.ybcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.yhutil.userFormUtil;
import project.ppaya.square.yhvo.Group;

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
	public String memberPhotoForm(Model model) {
		logger.info("개인앨범입니다!");

		String user_id = "id1";
		
		model.addAttribute("group_list", userFormUtil.getGroupByUserId(user_id));
		
		return "member/memberPhotoForm";
	}
	
}
