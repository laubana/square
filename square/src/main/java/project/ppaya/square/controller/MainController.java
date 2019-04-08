package project.ppaya.square.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.shdao.SH_DAO_Group;
import project.ppaya.square.vo.GroupCategory;



@Controller
public class MainController
{
	
@Autowired
SH_DAO_Group sh_gdao;
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String mainForm(Model request)
	{
		/* 나중에 groupCategory 테이블에 튜플 많이 들어가면 살리기
		ArrayList<GroupCategory> clist = null;
		clist = sh_gdao.getCategoryList();
		*/
		ArrayList<GroupCategory> clist = new ArrayList<>();
		int i = 0;
		if(clist != null) {
			request.addAttribute("clist",clist);
			for(i = 1 ; i <= 6; i = i +1){
				clist.add(new GroupCategory(i,"temp_name"+i));
			}
			
			logger.info("메인입니다!");
		} 
		
		return "main/mainForm";

	}

}