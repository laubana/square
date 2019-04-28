package project.ppaya.square.yhcontroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import project.ppaya.square.yhthread.YHUpdateEventScheduleImageFaceThread;
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
	YHEventScheduleCommentDAO yh_event_schedule_commentDAO;
	@Autowired
	YHEventScheduleCommentTagDAO yh_event_schedule_comment_tagDAO;
	@Autowired
	YHEventScheduleImageDAO yh_event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageTagDAO yh_event_schedule_image_tagDAO;
	@Autowired
	YHEventScheduleImageDescriptionDAO yh_event_schedule_image_descriptionDAO;
	@Autowired
	YHEventScheduleImageCategoryDAO yh_event_schedule_image_categoryDAO;
	@Autowired
	YHCommentTagDAO yh_comment_tagDAO;
	
	@Autowired
	YHUtil yh_util;
	
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
		
		YHMSFaceUtil.getFace(Reference.event_schedule_image_path, "event_schedule2_image1.jpg");
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
	@RequestMapping(value = "yhtest2", method = RequestMethod.GET)
	public void yhtest2(Model request)
	{
		System.out.println(YHMSTextAnalyticsUtil.getKeyPhraseList("Javaを勉強したいです。", "ja").toString());
		System.out.println(YHMSTextAnalyticsUtil.getKeyPhraseList("Javascriptができる方いますか？", "ja").toString());
		System.out.println(YHMSTextAnalyticsUtil.getKeyPhraseList("SQLは簡単ですよ。", "ja").toString());
	}
	@RequestMapping(value = "yhtest3", method = RequestMethod.GET)
	public String yhtest3(Model request)
	{
		return "yhtest/yhtest3";
	}
	@RequestMapping(value = "yhtest4", method = RequestMethod.GET)
	public void yhtest4(Model request)
	{
		try
		{
			logger.debug("{}", (new SimpleDateFormat("HH:mm:ss.SSS")).parse("00:01:45.946").getTime());
		}
		catch(Exception error){error.printStackTrace();}
	}
	@RequestMapping(value = "yhtest5", method = RequestMethod.GET)
	public void yhtest5(Model request)
	{
		Calendar current_date = Calendar.getInstance();
		
		current_date.set(Calendar.SECOND, 0);
		current_date.set(Calendar.MILLISECOND, 0);
		logger.debug("{}", current_date.getTime());
		logger.debug("{}", current_date.getTime().getTime());
	}
	@RequestMapping(value = "yhtest6", method = RequestMethod.GET)
	public void yhtest6(Model request)
	{
		String user_id = "id1@gmail.com";
		yh_util.updateEventScheduleVideoFace(user_id);
		yh_util.updateVideoAlbum(user_id);
	}
	@RequestMapping(value = "yhinit", method = RequestMethod.GET)
	public void yhinit()
	{
		ArrayList<GroupComment> group_comment_list = yh_group_commentDAO.selectGroupCommentByGroupId(1);		
		for(int i = 0; i < group_comment_list.size(); i++)
		{
			ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(group_comment_list.get(i).getContent(), "en");
			
			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_group_comment_tagDAO.insertGroupCommentTag(group_comment_list.get(i).getGroup_comment_id(), source_tag_list.get(j));
				yh_comment_tagDAO.insertCommentTag(group_comment_list.get(i).getUser_id(), source_tag_list.get(j));
			}
		}
		
		ArrayList<Integer> event_id_list = yh_eventDAO.getEventIdByGroupId(1);		
		ArrayList<EventComment> event_comment_list = yh_event_commentDAO.selectEventCommentByEventIdList(event_id_list);
		
		for(int i = 0; i < event_comment_list.size(); i++)
		{	
			ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(event_comment_list.get(i).getContent(), "en");
			
			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_event_comment_tagDAO.insertEventCommentTag(event_comment_list.get(i).getEvent_comment_id(), source_tag_list.get(j));
				yh_comment_tagDAO.insertCommentTag(event_comment_list.get(i).getUser_id(), source_tag_list.get(j));
			}
		}
		
		ArrayList<Integer> event_schedule_id_list = yh_event_scheduleDAO.getEventScheduleIdByEventIdList(event_id_list);
		ArrayList<EventScheduleComment> event_schedule_comment_list = yh_event_schedule_commentDAO.selectEventScheduleCommentByEventScheduleIdList(event_schedule_id_list);
		
		for(int i = 0; i < event_schedule_comment_list.size(); i++)
		{	
			ArrayList<String> source_tag_list = YHMSTextAnalyticsUtil.getKeyPhraseList(event_schedule_comment_list.get(i).getContent(), "en");
			
			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "en", "ja"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_event_schedule_comment_tagDAO.insertEventScheduleCommentTag(event_comment_list.get(i).getEvent_comment_id(), source_tag_list.get(j));
				yh_comment_tagDAO.insertCommentTag(event_schedule_comment_list.get(i).getUser_id(), source_tag_list.get(j));
			}
		}		
		
		ArrayList<EventScheduleImage> event_schedule_image_list = yh_event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		/*for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			ArrayList<String> source_tag_list = YHMSComputerVisionUtil.getTagList(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getFilename(), "ja");

			ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_tag_list.get(j), "ja", "ko"));
			}
			
			for(int j = 0; j < source_tag_list.size(); j++)
			{
				yh_event_schedule_image_tagDAO.insertEventScheduleImageTag(event_schedule_image_list.get(i).getEvent_schedule_image_id(), source_tag_list.get(j));
			}
		}*/
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			ArrayList<String> source_description_list = YHMSComputerVisionUtil.getDescriptionList(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getFilename(), "ja");
			
			/*ArrayList<String> target_tag_list = new ArrayList<>();
			for(int j = 0; j < source_description_list.size(); j++)
			{
				target_tag_list.add(YHGoogleTranslationUtil.getTranslation(source_description_list.get(j), "ja", "ko"));
			}*/
			
			for(int j = 0; j < source_description_list.size(); j++)
			{
				yh_event_schedule_imageDAO.updateDescriptionByEventScheduleImageId(source_description_list.get(j), event_schedule_image_list.get(i).getEvent_schedule_image_id());
			}
		}
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			ArrayList<String> source_category_list = YHMSComputerVisionUtil.getCategoryList(Reference.event_schedule_image_path, event_schedule_image_list.get(i).getFilename(), "ja");
			
			/*ArrayList<String> target_category_list = new ArrayList<>();
			for(int j = 0; j < source_category_list.size(); j++)
			{
				target_category_list.add(YHGoogleTranslationUtil.getTranslation(source_category_list.get(j), "ja", "ko"));
			}*/
			
			for(int j = 0; j < source_category_list.size(); j++)
			{
				yh_event_schedule_image_categoryDAO.insertEventScheduleImageCategory(event_schedule_image_list.get(i).getEvent_schedule_image_id(), source_category_list.get(j));
			}
		}
		
		yh_util.updateEventScheduleImageFace("id1@gmail.com");
		yh_util.updateImageAlbum("id1@gmail.com");
		yh_util.updateEventScheduleVideoFace("id1@gmail.com");
		yh_util.updateVideoAlbum("id1@gmail.com");
	}
}
