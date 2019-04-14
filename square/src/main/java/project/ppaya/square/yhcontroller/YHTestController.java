package project.ppaya.square.yhcontroller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhutil.*;

@Repository
@Controller
public class YHTestController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestController.class);
	
	@RequestMapping(value = "yhtest", method = RequestMethod.GET)
	public void YHTest()
	{
		/*ArrayList<String> phrase_list = YHMSTextAnalyticsUtil.getKeyPhraseList("test");		
		logger.debug("{}", phrase_list.toString());
		
		ArrayList<String> tag_list = YHMSComputerVisionUtil.getTagList(Reference.event_schedule_image_path, "tag.jpg");		
		logger.debug("{}", tag_list.toString());
		
		ArrayList<String> descripton_list = YHMSComputerVisionUtil.getDescriptionList(Reference.event_schedule_image_path, "description.jpg");		
		logger.debug("{}", descripton_list.toString());
		
		ArrayList<String> brand_list = YHMSComputerVisionUtil.getBrandList(Reference.event_schedule_image_path, "brand.jpg");		
		logger.debug("{}", brand_list.toString());
		
		ArrayList<String> category_list = YHMSComputerVisionUtil.getCategoryList(Reference.event_schedule_image_path, "category.png");		
		logger.debug("{}", category_list.toString());
		
		double sentiment = YHMSTextAnalyticsUtil.getSentiment("Unfortunately, it rained during my entire trip to Seattle. I didn't even get to visit the Space Needle");		
		logger.debug("{}", sentiment);*/
		
		ArrayList<ArrayList<String>> text_list_list = YHMSComputerVisionUtil.setTextList(Reference.event_schedule_image_path, "text.jpg", "Handwritten");			
		logger.debug("{}", text_list_list.toString());
	}
}
