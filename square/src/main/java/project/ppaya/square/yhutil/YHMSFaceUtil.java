package project.ppaya.square.yhutil;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;

public class YHMSFaceUtil
{	
	public static ArrayList<String> getSimilarEventScheduleImageFaceIdByFaceId(ArrayList<String> face_id_list, String face_id)
	{
		ArrayList<String> similar_event_schedule_image_face_id_list = new ArrayList<>();
		YHLogDAO yh_logDAO = (YHLogDAO)YHBeanUtil.getBean("YHLogDAO");
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
        
		HttpClient httpClient = HttpClients.createDefault();
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars");
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
            
            httpPost.setEntity(new StringEntity(
            		"{" +
            				"\"faceId\":\"" + face_id + "\"," +
            				"\"faceIds\":" + temp + "," +
            				"\"maxNumOfCandidatesReturned\":1000" +
            		"}"));
            
            while(true)
            {
            	HttpResponse httpResponse = httpClient.execute(httpPost);
            	result = EntityUtils.toString(httpResponse.getEntity()).trim();

            	System.err.println(result);
            	yh_logDAO.insertLog(result);
            	
            	if(result.charAt(0) == '{')
            	{
            		jsonObject = new JSONObject(result);
            		
            		if(jsonObject.getJSONObject("error").getString("code").equals("RateLimitExceeded"))
            		{
            			Thread.sleep(1000);
            			continue;
            		}
            		else
            		{
            			return null;
            		}
            	}
            	else
            	{
                    jsonArray = new JSONArray(result);
                    
                    for(int i = 0; i <jsonArray.length(); i++)
                    {
                    	jsonObject = jsonArray.getJSONObject(i);
                    	
                    	similar_event_schedule_image_face_id_list.add(jsonObject.getString("faceId"));
                    }
                    
                    return similar_event_schedule_image_face_id_list;
            	}
            }
        }
        catch (Exception error)
        {
        	error.printStackTrace();
            return null;
        }
	}
	public static String getFaceId(String path, String file)
	{			
        String result;
		HttpClient httpClient= HttpClientBuilder.create().build();  
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");
			
			uriBuilder.setParameter("returnFaceId", "true");
            
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/octet-stream");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
            
            httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));
        	
            while(true)
            {
            	HttpResponse httpResponse = httpClient.execute(httpPost);
            	result = EntityUtils.toString(httpResponse.getEntity()).trim();

            	System.err.println(result);
            	
            	if(result.charAt(0) == '{')
            	{
            		Thread.sleep(500);
            	}
            	else
            	{                    
                    return (new JSONArray(result)).getJSONObject(0).getString("faceId");
            	}
            }
        }
        catch(Exception error)
        {
        	error.printStackTrace();
            return null;
        }
	}
	public static String getFace(String path, String file)
	{			
        String result;
		HttpClient httpClient= HttpClientBuilder.create().build();  
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");
			
			uriBuilder.setParameter("returnFaceId", "true");
            
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/octet-stream");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
            
            httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

            while(true)
            {
            	HttpResponse httpResponse = httpClient.execute(httpPost);
            	result = EntityUtils.toString(httpResponse.getEntity()).trim();

            	System.err.println(result);
            	
            	if(result.charAt(0) == '{')
            	{
            		Thread.sleep(500);
            		continue;
            	}
            	else
            	{                    
                    return result;
            	}
            }           
        }
        catch(Exception error)
        {
        	error.printStackTrace();
            return error.getMessage();
        }
	}
}
