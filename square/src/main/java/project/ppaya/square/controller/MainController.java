package project.ppaya.square.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.shdao.*;
import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;



@Controller
public class MainController
{ 
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	SH_DAO_Group sh_gdao;
	
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	@Autowired
	YHGroupCategoryDAO yh_group_categoryDAO;

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String mainForm(Model request)
	{
		ArrayList<GroupCategory> group_category_list = yh_group_categoryDAO.selectGroupCategory();
		
		request.addAttribute("group_category_list", group_category_list);
		
		return "main/main";

	}
	@RequestMapping(value = "viewMindMapForm", method = RequestMethod.GET)
	public String viewMindMapForm(Model request)
	{
		String hashtag = "Java";
		
		ArrayList<Integer> group_id_list1 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag);
		ArrayList<Group> group_list1 = yh_groupDAO.selectGroupByGroupIdList(group_id_list1);
		
		ArrayList<Object> list1 = new ArrayList<>();
		for(int i = 0; i < group_list1.size(); i++)
		{
			HashMap<String, Object> map1 = new HashMap<>();
			
			map1.put("node", group_list1.get(i));
			
			ArrayList<String> hashtag_list1 = yh_group_hashtagDAO.getHashtagByGroupId(group_list1.get(i).getGroup_id());	
			
			ArrayList<Object> list2 = new ArrayList<>();
			
			for(int j = 0; j < hashtag_list1.size(); j++)
			{
				HashMap<String, Object> map2 = new HashMap<>();
				
				map2.put("node", hashtag_list1.get(j));
				
				ArrayList<Integer> group_id_list2 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list1.get(j));
				ArrayList<Group> group_list2 = yh_groupDAO.selectGroupByGroupIdList(group_id_list2);
				
				ArrayList<Object> list3 = new ArrayList<>();
				
				for(int k = 0; k < group_list2.size(); k++)
				{
					HashMap<String, Object> map3 = new HashMap<>();
					
					map3.put("node", group_list2.get(k));
					
					ArrayList<String> hashtag_list2 = yh_group_hashtagDAO.getHashtagByGroupId(group_list2.get(k).getGroup_id());
					
					map3.put("list", hashtag_list2);
					
					list3.add(map3);
				}
				
				map2.put("list", list3);
				
				list2.add(map2);
			}
			
			map1.put("list", list2);
			
			list1.add(map1);
		}
		request.addAttribute("list", new JSONArray(list1));
			
			/*for(int j = 0; j < hashtag_list1.size(); j++)
			{
				ArrayList<Integer> group_id_list2 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list1.get(j));
				ArrayList<Group> group_list2 = yh_groupDAO.selectGroupByGroupIdList(group_id_list2);
				
				ArrayList<Object> list2 = new ArrayList<>();
				for(int k = 0; k < group_list2.size(); k++)
				{
					HashMap<String, Object> map2 = new HashMap<>();
					
					map2.put("group", group_list2.get(k));
					ArrayList<String> hashtag_list2 = yh_group_hashtagDAO.getHashtagByGroupId(group_list2.get(k).getGroup_id());
					for(int l = 0; l < hashtag_list2.size(); l++)
					{
						ArrayList<Integer> group_id_list3 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list2.get(l));
						ArrayList<Group> group_list3 = yh_groupDAO.selectGroupByGroupIdList(group_id_list3);
						
						ArrayList<Object> list3 = new ArrayList<>();
						for(int m = 0; m < group_list3.size(); m++)
						{
							HashMap<String, Object> map3 = new HashMap<>();
							
							map3.put("group", group_list3.get(m));
							
							map3.put("hashtag_list", yh_group_hashtagDAO.getHashtagByGroupId(group_list3.get(m).getGroup_id()));
							
							list3.add(map3);
						}
					}
					
					map2.put("hashtag_list", hashtag_list2);
					
					list2.add(map2);
				}
			}
			
			map1.put("hashtag_list", hashtag_list1);
			
			list1.add(map1);
		}*/
			
		
		return "main/viewMindMapForm";

	}
}