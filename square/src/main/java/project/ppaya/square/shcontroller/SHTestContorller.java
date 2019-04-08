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





}
