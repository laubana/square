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






/*
 @RequestMapping(value = "myPage", method = RequestMethod.GET)
public String sh_myPageForm(Model request)
{
	//그 사람이 참여 중인 그룹 리스트 보내기
	String userid = "id1"; //세현: 나중에는 세션에서 id 받아서 넣기. 일단 임시로 넣어 둠
	ArrayList<Group> glist = sh_gdao.getGroupByUser(userid);
	request.addAttribute("glist",glist);
	
	//해시태그 보내기

나중에 user_hashtag 테이블 생성되고 나면 살리기 
		ArrayList<UserHashtag> hlist = null;
	hlist = sh_udao.getUserHashtag(userid);
일단 아래는 임시
		
	ArrayList<UserHashtag> hlist = null;
	//hlist = sh_udao.getUserHashtag(userid);
	
	hlist = new ArrayList<UserHashtag>();
	UserHashtag h1 = new UserHashtag();
	int i = 0;
	for(i = 3 ; i <= 7 ; i = i + 1){
		h1.setUser_id("tempid"+i);
		h1.setHashtag_id(i);
		hlist.add(h1);
	}
	request.addAttribute("hlist",hlist);

	
	return "member/myPageForm";
}
*/
/*
	@RequestMapping(value = "groupSearch", method = RequestMethod.GET)
	public String searchForm(
			String category
			, Model request) {
		
		String testint = "1"; //나중에 jsp에서 값 받을 거. TABLE_G 테이블의 group_category_id 찾을 값.
		int ctg = Integer.parseInt(testint);
		ArrayList<Group> glist = null;
		//glist = sh_gdao.getGroupByCategory(ctg); 일단 배열에 임시로 객체 넣어 놓기

		glist = new ArrayList<>();
		int i = 0; 	Group g1 = new Group();
		for(i = 0; i <= 6; i = i + 1){
			g1.setContent("tempContent" + 1);
			g1.setName("tempName"+1);
			g1.setGroup_category_id(i);
			g1.setGroup_id(i);
			g1.setRegion("tempRegn"+i);
			glist.add(g1);
		}
		request.addAttribute("glist", glist);
		logger.info("그룹탐색입니다!");

		return "group/groupSearchForm";
	}
*/


}
