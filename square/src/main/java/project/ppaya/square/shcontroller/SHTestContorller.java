package project.ppaya.square.shcontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.shdao.SH_DAO_User;
import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.User;
import project.ppaya.square.vo.UserHashtag;

@Controller
public class SHTestContorller {
Logger logger = Logger.getLogger(SHTestContorller.class);
	
@Autowired
SH_DAO_Group sh_gdao;

@Autowired
SH_DAO_User sh_udao;

@ResponseBody
@RequestMapping(value = "groupSearchByKeyword", method = RequestMethod.GET)
public ArrayList<Group> response_GroupSearch(String keyword){
	
	ArrayList<Group> glist = null;
	glist = sh_gdao.getGroupByKeyword(keyword);
	
	int i = 0; 	Group g1 = new Group();
	for(i = 0; i <= 6; i = i + 1){
		g1.setContent("tempContent" + 1);
		g1.setName("tempName"+1);
		g1.setGroup_category_id(i);
		g1.setGroup_id(i);
		g1.setRegion("tempRegn"+i);
		glist.add(g1);
	}
	
	return glist;
}

@RequestMapping(value = "joinForm2", method = RequestMethod.GET)
public String joinForm2(User user)
{
	User user1 = new User();
	user1.setUser_id("tes2");
	user1.setPassword("tes2");
	user1.setRegion("test2_reg");
	user1.setName("tes2");
	
	sh_udao.inputUser(user1);
	
	
	logger.info("join2입니다!");
	//세현: 현재 joinForm 에서 보내주는 값들에 대해, 팀 내의 논의가 필요하므로 일단 보류해놓고 나중에 계속 작업하겠음 
	
	
	return "main/mainForm";
}



}
