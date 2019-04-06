package project.ppaya.square.shcontroller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.shdao.SH_DAO_User;

@Controller
public class SHTestContorller {
Logger logger = Logger.getLogger(SHTestContorller.class);
	
}
/*
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.vo.GroupCategory;


@Autowired
SH_DAO_Group sh_gdao;

	
@RequestMapping(value = "main", method = RequestMethod.GET)
public String mainForm(Model request)
{
	//카테고리 리스트 받아 옴
	ArrayList<GroupCategory> clist = null;
	clist = sh_gdao.getCategoryList();
	int i = 0;
	if(clist != null) {
		request.addAttribute("clist",clist);
		for(i = 1 ; i <= 5; i = i +1){
			clist.add(new GroupCategory(i,"temp_name"+i);
		}
		
		logger.info("메인입니다!");
	} 
	
	else {
		GroupCategory g1 = new GroupCategory("오류입니다");
		clist = new ArrayList<GroupCategory>();
		clist.add(g1);
		request.addAttribute("clist", clist);
	}
	return "main/mainForm";

}
*/