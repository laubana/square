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
import project.ppaya.square.yhdao.YHGroupCategoryDAO;



@Controller
public class MainController
{ 
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	SH_DAO_Group sh_gdao;
	@Autowired
	YHGroupCategoryDAO yh_group_categoryDAO;

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String mainForm(Model request)
	{
		ArrayList<GroupCategory> group_category_list = yh_group_categoryDAO.selectGroupCategory();
		
		request.addAttribute("group_category_list", group_category_list);
		
		return "main/main";

	}
}