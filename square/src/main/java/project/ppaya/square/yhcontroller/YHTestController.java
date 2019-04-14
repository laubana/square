package project.ppaya.square.yhcontroller;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class YHTestController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestController.class);
	
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupCommentDAO yh_group_commentDAO;
	@Autowired
	YHGroupCommentTagDAO yh_group_comment_tagDAO;
	@Autowired
	YHEventDAO yh_eventDAO;
	@Autowired
	YHEventCommentDAO yh_event_commentDAO;
	@Autowired
	YHEventCommentTagDAO yh_event_comment_tagDAO;
	@Autowired
	YHEventScheduleDAO yh_event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageTagDAO yh_event_schedule_image_tagDAO;
	@Autowired
	YHEventScheduleImageDescriptionDAO yh_event_schedule_image_descriptionDAO;
	@Autowired
	YHEventScheduleImageCategoryDAO yh_event_schedule_image_categoryDAO;
	
	@RequestMapping(value = "yhtest", method = RequestMethod.GET)
	public void YHTest()
	{
		/*ArrayList<String> phrase_list = YHMSTextAnalyticsUtil.getKeyPhraseList("SQL is easy to study.");		
		logger.debug("{}", phrase_list.toString());
		
		ArrayList<String> tag_list = YHMSComputerVisionUtil.getTagList(Reference.event_schedule_image_path, "");		
		logger.debug("{}", tag_list.toString());

		ArrayList<String> brand_list = YHMSComputerVisionUtil.getBrandList(Reference.event_schedule_image_path, "brand.jpg");		
		logger.debug("{}", brand_list.toString());
		
		ArrayList<String> category_list = YHMSComputerVisionUtil.getCategoryList(Reference.event_schedule_image_path, "category.png");		
		logger.debug("{}", category_list.toString());
		
		double sentiment = YHMSTextAnalyticsUtil.getSentiment("Unfortunately, it rained during my entire trip to Seattle. I didn't even get to visit the Space Needle");		
		logger.debug("{}", sentiment);
		
		ArrayList<ArrayList<String>> text_list_list = YHMSComputerVisionUtil.setTextList(Reference.event_schedule_image_path, "text.jpg", "Handwritten");			
		logger.debug("{}", text_list_list.toString());
		
		ArrayList<String> descripton_list = YHMSComputerVisionUtil.getDescriptionList(Reference.event_schedule_image_path, "event_schedule1_image" + i + ".jpg");		
		logger.debug("{}", descripton_list.toString());*/

		/*for(int i = 1; i <= 4; i++)
		{
			ArrayList<String> category_list = YHMSComputerVisionUtil.getCategoryList(Reference.event_schedule_image_path, "event_schedule1_image" + i + ".jpg");		
			logger.debug("{}", category_list.toString());			
		}*/
	}
	@RequestMapping(value = "yhtest1", method = RequestMethod.GET)
	public String yhtest1(Model request)
	{
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByUserId("id1@gmail.com");
		
		ArrayList<Object> list1 = new ArrayList<>();		
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			HashMap<String, Object> map1 = new HashMap<>();			
			
			map1.put("group_comment", group_comment_list.get(i));
			ArrayList<GroupCommentTag> group_comment_tag_list = yh_group_comment_tagDAO.selectGroupCommentTagByGroupCommentId(group_comment_list.get(i).getGroup_comment_id());
			
			ArrayList<Object> list2 = new ArrayList<>();
			for(int j = 0; j < group_comment_tag_list.size(); j++)
			{
				HashMap<String, Object> map2 = new HashMap<>();
				
				map2.put("group_comment_tag", group_comment_tag_list.get(j));
				map2.put("group_list", yh_groupDAO.selectGroupByName(group_comment_tag_list.get(j).getTag()));
				
				list2.add(map2);
			}
			map1.put("group_comment_tag_list", list2);
			
			list1.add(map1);
		}
		
		request.addAttribute("list", list1);
		request.addAttribute("json_list", new JSONArray(list1));		
		
		return "yhtest/yhtest1";
	}
	@RequestMapping(value = "yhinit", method = RequestMethod.GET)
	public void yhinit()
	{
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(1);		
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			ArrayList<String> tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(group_comment_list.get(i).getContent(), "en");
			
			for(int j = 0; j < tag_list.size(); j++)
			{
				yh_group_comment_tagDAO.insertGroupCommentTag(group_comment_list.get(i).getGroup_comment_id(), tag_list.get(j));
			}
		}
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(1);		
		ArrayList<EventComment> event_comment_list = yh_event_commentDAO.selectEventCommentByEventIdList(event_id_list);
		
		for(int i = 0; i < event_comment_list.size(); i++)
		{	
			ArrayList<String> tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(event_comment_list.get(i).getContent(), "en");
			
			for(int j = 0; j < tag_list.size(); j++)
			{
				yh_event_comment_tagDAO.insertEventCommentTag(event_comment_list.get(i).getEvent_comment_id(), tag_list.get(j));
			}
		}
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			ArrayList<String> tag_list = YHMSComputerVisionUtil.getTagList(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getFilename(), "en");
			
			for(int j = 0; j < tag_list.size(); j++)
			{
				yh_event_schedule_image_tagDAO.insertEventScheduleImageTag(event_schedule_image_list.get(i).getEvent_schedule_image_id(), tag_list.get(j));
			}
		}
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			ArrayList<String> description_list = YHMSComputerVisionUtil.getDescriptionList(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getFilename(), "en");
			
			for(int j = 0; j < description_list.size(); j++)
			{
				yh_event_schedule_image_descriptionDAO.insertEventScheduleImageDescription(event_schedule_image_list.get(i).getEvent_schedule_image_id(), description_list.get(j));
			}
		}
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			ArrayList<String> category_list = YHMSComputerVisionUtil.getCategoryList(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getFilename(), "en");
			
			for(int j = 0; j < category_list.size(); j++)
			{
				yh_event_schedule_image_categoryDAO.insertEventScheduleImageCategory(event_schedule_image_list.get(i).getEvent_schedule_image_id(), category_list.get(j));
			}
		}
	}
}
