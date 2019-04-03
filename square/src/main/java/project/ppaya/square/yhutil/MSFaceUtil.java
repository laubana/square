package project.ppaya.square.yhutil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.ppaya.square.vo.Event;
import project.ppaya.square.vo.EventSchedule;
import project.ppaya.square.vo.EventScheduleImage;
import project.ppaya.square.vo.Reference;
import project.ppaya.square.yhdao.YHEventDAO;
import project.ppaya.square.yhdao.YHEventScheduleDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageDAO;
import project.ppaya.square.yhdao.YHEventScheduleImageFaceDAO;

@Repository
public class MSFaceUtil
{
	@Autowired
	YHEventDAO eventDAO;
	@Autowired
	YHEventScheduleDAO event_scheduleDAO;
	@Autowired
	YHEventScheduleImageDAO event_schedule_imageDAO;
	@Autowired
	YHEventScheduleImageFaceDAO event_schedule_image_faceDAO;
	
	public void updateEventScheduleImageFaceByGroupId(int group_id)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<Event> event_list = eventDAO.selectEventByGroupId(group_id);
		
		ArrayList<Integer> event_id_list = new ArrayList<>();
		for(int i = 0; i < event_list.size(); i++)
		{
			event_id_list.add(event_list.get(i).getEvent_id());
		}
		
		ArrayList<EventSchedule> event_schedule_list = event_scheduleDAO.selectEventScheduleByEventIdList(event_id_list);
		
		ArrayList<Integer> event_schedule_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_list.size(); i++)
		{
			event_schedule_id_list.add(event_schedule_list.get(i).getEvent_schedule_id());
		}
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
	public void updateEventScheduleImageFaceByGroupIdList(ArrayList<Integer> group_id_list)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<Event> event_list = eventDAO.selectEventByGroupIdList(group_id_list);
		
		ArrayList<Integer> event_id_list = new ArrayList<>();
		for(int i = 0; i < event_list.size(); i++)
		{
			event_id_list.add(event_list.get(i).getEvent_id());
		}
		
		ArrayList<EventSchedule> event_schedule_list = event_scheduleDAO.selectEventScheduleByEventIdList(event_id_list);
		
		ArrayList<Integer> event_schedule_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_list.size(); i++)
		{
			event_schedule_id_list.add(event_schedule_list.get(i).getEvent_schedule_id());
		}
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
	public void updateEventScheduleImageFaceByEventId(int event_id)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<EventSchedule> event_schedule_list = event_scheduleDAO.selectEventScheduleByEventId(event_id);
		
		ArrayList<Integer> event_schedule_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_list.size(); i++)
		{
			event_schedule_id_list.add(event_schedule_list.get(i).getEvent_schedule_id());
		}
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
	public void updateEventScheduleImageFaceByEventIdList(ArrayList<Integer> event_id_list)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<EventSchedule> event_schedule_list = event_scheduleDAO.selectEventScheduleByEventIdList(event_id_list);
		
		ArrayList<Integer> event_schedule_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_list.size(); i++)
		{
			event_schedule_id_list.add(event_schedule_list.get(i).getEvent_schedule_id());
		}
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
	public void updateEventScheduleImageFaceByEventScheduleId(int event_schedule_id)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleId(event_schedule_id);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
	public void updateEventScheduleImageFaceByEventScheduleIdList(ArrayList<Integer> event_schedule_id_list)
	{
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		
		ArrayList<EventScheduleImage> event_schedule_image_list = event_schedule_imageDAO.selectEventScheduleImageByEventScheduleIdList(event_schedule_id_list);
		
		ArrayList<String> event_schedule_image_id_list = new ArrayList<>();
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			event_schedule_image_id_list.add(event_schedule_image_list.get(i).getEvent_schedule_image_id());
		}
		
		for(int i = 0; i < event_schedule_image_list.size(); i++)
		{
			if(event_schedule_image_list.get(i).getDetect_date() == null)
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.insertEventScheduleImageFace(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
			else if(3600000 < (new Date()).getTime() - event_schedule_image_list.get(i).getDetect_date())
			{
				event_schedule_imageDAO.updateEventScheduleImageDetectDateByEventScheduleImageId(event_schedule_image_id_list.get(i), (new Date()).getTime());
				
				result = MSFaceUtil.detectFace(Reference.file_path, event_schedule_image_id_list.get(i));
				jsonArray = new JSONArray(result);
				
				for(int j = 0; j < jsonArray.length(); j++)
				{
					jsonObject = jsonArray.getJSONObject(j);
					
					event_schedule_image_faceDAO.updateEventScheduleImageFaceIdByEventScheduleImageIdRectangle(jsonObject.getString("faceId"), event_schedule_image_id_list.get(i),
							jsonObject.getJSONObject("faceRectangle").getInt("top"),
							jsonObject.getJSONObject("faceRectangle").getInt("left"),
							jsonObject.getJSONObject("faceRectangle").getInt("width"),
							jsonObject.getJSONObject("faceRectangle").getInt("height")
							);
				}
			}
		}
	}
	public static ArrayList<String> getSimilarEventScheduleImageFaceIdListByFaceId(ArrayList<String> face_id_list, String face_id)
	{
		ArrayList<String> similar_event_schedule_image_face_id_list = new ArrayList<>();
		String result = null;
		JSONArray jsonArray = null;
		JSONObject jsonObject = null;
		String temp = "";
		temp += "[";
		for(int i = 0; i < face_id_list.size() - 1; i++)
		{
			temp += "\"";
			temp += face_id_list.get(i);
			temp += "\",";
		}
		temp += "\"" + face_id_list.get(face_id_list.size() - 1) + "\"";
		temp += "]";

        System.out.println(face_id);
        System.out.println(temp);
        
		HttpClient httpclient = HttpClients.createDefault();
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars");
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
            
            httpPost.setEntity(new StringEntity(
            		"{" +
            				"\"faceId\":\"" + face_id + "\"," +
            				"\"faceIds\":" + temp + "," +
            				"\"maxNumOfCandidatesReturned\":1000" +
            		"}"));
            
            HttpResponse httpResponse = httpclient.execute(httpPost);
            
            result = EntityUtils.toString(httpResponse.getEntity()).trim();

            jsonArray = new JSONArray(result);
            
            for(int i = 0; i <jsonArray.length(); i++)
            {
            	jsonObject = jsonArray.getJSONObject(i);
            	
            	similar_event_schedule_image_face_id_list.add(jsonObject.getString("faceId"));
            }
            
            return similar_event_schedule_image_face_id_list;
        }
        catch (Exception error)
        {
        	error.printStackTrace();
            return null;
        }
	}
	public static String getSimilarEventScheduleImageFaceListByFaceId(ArrayList<String> face_id_list, String face_id)
	{
		String temp = "";
		temp += "[";
		for(int i = 0; i < face_id_list.size() - 1; i++)
		{
			temp += "\"";
			temp += face_id_list.get(i);
			temp += "\",";
		}
		temp += "\"" + face_id_list.get(face_id_list.size() - 1) + "\"";
		temp += "]";
		
		HttpClient httpclient = HttpClients.createDefault();
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars");
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
            
            httpPost.setEntity(new StringEntity(
            		"{" +
            				"\"faceId\":\"" + face_id + "\"," +
            				"\"faceIds\":" + temp + "," +
            				"\"maxNumOfCandidatesReturned\":1000" +
            		"}"));
            
            HttpResponse httpResponse = httpclient.execute(httpPost);
            
            return EntityUtils.toString(httpResponse.getEntity()).trim();
        }
        catch (Exception error)
        {
            return error.getMessage();
        }
	}
	public static String getSimilarFace(String list_id, String face_id)
	{		
		HttpClient httpclient = HttpClients.createDefault();
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars");
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
            
            httpPost.setEntity(new StringEntity(
            		"{" +
            				"\"faceListId\":\"" + list_id + "\"," +
            				"\"faceId\":\"" + face_id + "\"," +
            				"\"maxNumOfCandidatesReturned\":1000" +
            		"}"));
            
            HttpResponse httpResponse = httpclient.execute(httpPost);
            
            return EntityUtils.toString(httpResponse.getEntity()).trim();
        }
        catch (Exception error)
        {
            return error.getMessage();
        }
	}
	public static String detectFace(String path, String file)
	{
		HttpClient httpclient = HttpClients.createDefault();
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");
			
			uriBuilder.setParameter("returnFaceId", "true");
            
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/octet-stream");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
            
            httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

            HttpResponse httpResponse = httpclient.execute(httpPost);
            
            return EntityUtils.toString(httpResponse.getEntity()).trim();
        }
        catch (Exception error)
        {
            return error.getMessage();
        }
	}
	public static String insertFace(String list_id, String path, String file)
	{
		HttpClient httpclient = HttpClients.createDefault();
		
		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + list_id + "/persistedFaces");
		 
		    HttpPost httpPost = new HttpPost(uriBuilder.build());
		    
		    httpPost.setHeader("Content-Type", "application/octet-stream");
		    httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
		    
		    httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));
		    
		    HttpResponse httpResponse = httpclient.execute(httpPost);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
		    return error.getMessage();
		}
	}
	public static String insertFace(String list_id, String path, String file, int left, int top, int width, int height)
	{
		HttpClient httpclient = HttpClients.createDefault();
		
		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + list_id + "/persistedFaces");
			
		    uriBuilder.setParameter("targetFace", left + "," + top + "," + width + "," + height);
		 
		    HttpPost httpPost = new HttpPost(uriBuilder.build());
		    
		    httpPost.setHeader("Content-Type", "application/octet-stream");
		    httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
		    
		    httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));
		    
		    HttpResponse httpResponse = httpclient.execute(httpPost);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
		    return error.getMessage();
		}
	}
	public static String getFaceList(String id)
	{
		HttpClient httpclient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);
			
			HttpGet httpGet = new HttpGet(uriBuilder.build());
		    
		    httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
		    
		    HttpResponse httpResponse = httpclient.execute(httpGet);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
		    return error.getMessage();
		}
	}
	public static String deleteFaceList(String id)
	{
		HttpClient httpclient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);
			
			HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
		    
		    httpDelete.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
		    
		    HttpResponse httpResponse = httpclient.execute(httpDelete);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
		    return error.getMessage();
		}
	}
	public static String createFaceList(String id, String name)
	{
		HttpClient httpclient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);
			
			HttpPut httpPut = new HttpPut(uriBuilder.build());
		    
		    httpPut.setHeader("Content-Type", "application/json");
		    httpPut.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
		    
		    httpPut.setEntity(new StringEntity("{\"name\":\"" + name + "\"}"));
		    
		    HttpResponse httpResponse = httpclient.execute(httpPut);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
		    return error.getMessage();
		}
	}
	public static String getMultipleFaceList()
	{
		HttpClient httpclient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists");
			
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			
		    httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_key);
		   
		    HttpResponse httpResponse = httpclient.execute(httpGet);
		    
		    return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
		    return error.getMessage();
		}
	}
}
