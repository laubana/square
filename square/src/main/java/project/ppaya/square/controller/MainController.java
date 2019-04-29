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
import org.springframework.web.bind.annotation.RequestParam;

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
	YHEventDAO yh_eventDAO;
	@Autowired
	YHGroupHashtagDAO yh_group_hashtagDAO;
	@Autowired
	YHGroupCategoryDAO yh_group_categoryDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageDescriptionDAO yh_event_schedule_image_descriptionDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHKeywordHistoryDAO yh_keyword_historyDAO;

	@RequestMapping(value = "listRecommendationForm", method = RequestMethod.GET)
	public String listRecommendationForm
	(
			Model request,
			String hashtag
			)
	{
		if(hashtag == null)
		{
			ArrayList<String> hashtag_list = yh_keyword_historyDAO.getKeywordByRank();
			//Keyword List 전송
			request.addAttribute("hashtag_list", hashtag_list);
			
			ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventSchedeuleImageOrderByInputdate(0);
			
			ArrayList<HashMap<String, Object>> image_list = new ArrayList<>();
			
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				Group group = yh_groupDAO.selectGroupByGroupId(event_schedule_image_list.get(i).getGroup_id());
				
				HashMap<String, Object> map = new HashMap<>();
				
				map.put("image", event_schedule_image_list.get(i));
				map.put("group", group);
				map.put("description", yh_event_schedule_image_descriptionDAO.getDescriptionByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id()));
				
				image_list.add(map);
			}
			//EventScheduleImage List 전송
			request.addAttribute("image_list", image_list);
		}
		else
		{
			ArrayList<String> hashtag_list = yh_keyword_historyDAO.getKeywordByRank();
			//Keyword List 전송
			request.addAttribute("hashtag_list", hashtag_list);
			
			ArrayList<Integer> group_id_list = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag);
			
			ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByGroupIdListOrderByInputdate(group_id_list, 0);
			
			ArrayList<HashMap<String, Object>> image_list = new ArrayList<>();
			
			for(int i = 0; i < event_schedule_image_list.size(); i++)
			{
				Group group = yh_groupDAO.selectGroupByGroupId(event_schedule_image_list.get(i).getGroup_id());
				
				HashMap<String, Object> map = new HashMap<>();
				
				map.put("image", event_schedule_image_list.get(i));
				map.put("group", group);
				map.put("description", yh_event_schedule_image_descriptionDAO.getDescriptionByEventScheduleImageId(event_schedule_image_list.get(i).getEvent_schedule_image_id()));
				
				image_list.add(map);
			}
			//EventScheduleImage List 전송
			request.addAttribute("image_list", image_list);
		}
		
		return "main/listRecommendationForm";
	}
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String mainForm(Model request)
	{
		logger.debug("{}", this.getClass().getResource("/").getPath());
		ArrayList<GroupCategory> group_category_list = yh_group_categoryDAO.selectGroupCategory();
		//GroupCategory List 전송
		request.addAttribute("group_category_list", group_category_list);
		
		ArrayList<String> hashtag_list = yh_keyword_historyDAO.getKeywordByRank();
		//Keyword List 전송
		request.addAttribute("hashtag_list", hashtag_list);
		
		return "main/main";

	}
	@RequestMapping(value = "viewMindMapForm", method = RequestMethod.GET)
	public String viewMindMapForm
	(
			Model request,
			@RequestParam(value = "hashtag", defaultValue = "Java") String hashtag
			)
	{
		ArrayList<String> hashtag_list = new ArrayList<>();
		
		hashtag_list.clear();		
		hashtag_list.add(hashtag);
		
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
				if(hashtag.equals(hashtag_list1.get(j)))
				{
					continue;
				}
				HashMap<String, Object> map2 = new HashMap<>();
				
				map2.put("node", hashtag_list1.get(j));
				
				ArrayList<Integer> group_id_list2 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list1.get(j));
				ArrayList<Group> group_list2 = yh_groupDAO.selectGroupByGroupIdList(group_id_list2);
				
				ArrayList<Object> list3 = new ArrayList<>();
				
				for(int k = 0; k < group_list2.size(); k++)
				{
					if(group_list1.get(i).getGroup_id() == group_list2.get(k).getGroup_id())
					{
						continue;
					}
					HashMap<String, Object> map3 = new HashMap<>();
					
					map3.put("node", group_list2.get(k));
					
					ArrayList<String> hashtag_list2 = yh_group_hashtagDAO.getHashtagByGroupId(group_list2.get(k).getGroup_id());
					
					ArrayList<Object> list4 = new ArrayList<>();
					
					for(int m = 0; m < hashtag_list2.size(); m++)
					{
						if(hashtag_list1.get(j).equals(hashtag_list2.get(m)))
						{
							continue;
						}
						HashMap<String, Object> map4 = new HashMap<>();
						
						map4.put("node", hashtag_list2.get(m));
						
						ArrayList<Integer> group_id_list3 = yh_group_hashtagDAO.getGroupIdByHashtag(hashtag_list2.get(m));
						ArrayList<Group> group_list3 = yh_groupDAO.selectGroupByGroupIdList(group_id_list3);
						
						map4.put("list", group_list3);
						
						list4.add(map4);
					}
					map3.put("list", list4);
					
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
		
		return "main/viewMindMapForm";
	}
}