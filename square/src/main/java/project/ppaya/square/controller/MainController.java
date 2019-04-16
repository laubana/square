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
		ArrayList<Integer> group_id_list = new ArrayList<>();
		ArrayList<String> hashtag_list = new ArrayList<>();
		String hashtag = "Java";
		hashtag_list.add(hashtag);
		
		ArrayList<Integer> group_id_list1 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag);
		ArrayList<Group> group_list1 = yh_groupDAO.selectGroupByGroupIdList(group_id_list1);
		
		ArrayList<Object> list1 = new ArrayList<>();
		for(int i = 0; i < group_list1.size(); i++)
		{
			if(group_id_list.contains(group_list1.get(i).getGroup_id()))
			{
				continue;
			}
			else
			{
				group_id_list.add(group_list1.get(i).getGroup_id());
			}
			HashMap<String, Object> map1 = new HashMap<>();
			
			map1.put("node", group_list1.get(i));
			
			ArrayList<String> hashtag_list1 = yh_group_hashtagDAO.getHashtagByGroupId(group_list1.get(i).getGroup_id());	
			
			ArrayList<Object> list2 = new ArrayList<>();
			
			for(int j = 0; j < hashtag_list1.size(); j++)
			{
				if(hashtag_list.contains(hashtag_list1.get(j)))
				{
					continue;
				}
				else
				{
					hashtag_list.add(hashtag_list1.get(j));
				}
				HashMap<String, Object> map2 = new HashMap<>();
				
				map2.put("node", hashtag_list1.get(j));
				
				ArrayList<Integer> group_id_list2 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list1.get(j));
				ArrayList<Group> group_list2 = yh_groupDAO.selectGroupByGroupIdList(group_id_list2);
				
				ArrayList<Object> list3 = new ArrayList<>();
				
				for(int k = 0; k < group_list2.size(); k++)
				{
					if(group_id_list.contains(group_list2.get(k).getGroup_id()))
					{
						continue;
					}
					else
					{
						group_id_list.add(group_list2.get(k).getGroup_id());
					}
					HashMap<String, Object> map3 = new HashMap<>();
					
					map3.put("node", group_list2.get(k));
					
					ArrayList<String> hashtag_list2 = yh_group_hashtagDAO.getHashtagByGroupId(group_list2.get(k).getGroup_id());
					
					ArrayList<Object> list4 = new ArrayList<>();
					
					for(int m = 0; m < hashtag_list2.size(); m++)
					{
						if(hashtag_list.contains(hashtag_list2.get(m)))
						{
							continue;
						}
						else
						{
							hashtag_list.add(hashtag_list2.get(m));
						}
						HashMap<String, Object> map4 = new HashMap<>();
						
						map4.put("node", hashtag_list2.get(m));
						
						ArrayList<Integer> group_id_list3 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list2.get(m));
						ArrayList<Group> group_list3 = yh_groupDAO.selectGroupByGroupIdList(group_id_list3);
						
						map4.put("list", group_list3);
						
						list4.add(map4);
					}
					map3.put("list", hashtag_list2);
					
					list3.add(map3);
				}
				
				map2.put("list", list3);
				
				list2.add(map2);
			}
			
			map1.put("list", list2);
			
			list1.add(map1);
		}
		request.addAttribute("json_list", new JSONArray(list1));
		request.addAttribute("list", list1);
		request.addAttribute("root", hashtag);
			
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