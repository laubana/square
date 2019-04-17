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

public class YHMSFaceUtil
{	
	public static ArrayList<String> getSimilarEventScheduleImageFaceIdByFaceId(ArrayList<String> face_id_list, String face_id)
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
            
            HttpResponse httpResponse = httpClient.execute(httpPost);
            
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
            
            HttpResponse httpResponse = httpClient.execute(httpPost);
            
            return EntityUtils.toString(httpResponse.getEntity()).trim();
        }
        catch (Exception error)
        {
        	error.printStackTrace();
            return error.getMessage();
        }
	}
	public static String getSimilarFace(String list_id, String face_id)
	{		
		HttpClient httpClient = HttpClients.createDefault();
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars");
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
            
            httpPost.setEntity(new StringEntity(
            		"{" +
            				"\"faceListId\":\"" + list_id + "\"," +
            				"\"faceId\":\"" + face_id + "\"," +
            				"\"maxNumOfCandidatesReturned\":1000" +
            		"}"));
            
            HttpResponse httpResponse = httpClient.execute(httpPost);
            
            return EntityUtils.toString(httpResponse.getEntity()).trim();
        }
        catch (Exception error)
        {
        	error.printStackTrace();
            return error.getMessage();
        }
	}
	public static String detectFace(String path, String file)
	{			
		HttpClient httpClient= HttpClientBuilder.create().build();  
		
        try
        {			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect");
			
			uriBuilder.setParameter("returnFaceId", "true");
            
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			
            httpPost.setHeader("Content-Type", "application/octet-stream");
            httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
            
            httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            String result = EntityUtils.toString(httpResponse.getEntity()).trim(); 
            
            return result;
        }
        catch(Exception error)
        {
        	error.printStackTrace();
            return error.getMessage();
        }
	}
	public static String insertFace(String list_id, String path, String file)
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + list_id + "/persistedFaces");
		 
		    HttpPost httpPost = new HttpPost(uriBuilder.build());
		    
		    httpPost.setHeader("Content-Type", "application/octet-stream");
		    httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
		    
		    httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));
		    
		    HttpResponse httpResponse = httpClient.execute(httpPost);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
			error.printStackTrace();
		    return error.getMessage();
		}
	}
	public static String insertFace(String list_id, String path, String file, int left, int top, int width, int height)
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + list_id + "/persistedFaces");
			
		    uriBuilder.setParameter("targetFace", left + "," + top + "," + width + "," + height);
		 
		    HttpPost httpPost = new HttpPost(uriBuilder.build());
		    
		    httpPost.setHeader("Content-Type", "application/octet-stream");
		    httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
		    
		    httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));
		    
		    HttpResponse httpResponse = httpClient.execute(httpPost);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
			error.printStackTrace();
		    return error.getMessage();
		}
	}
	public static String getFaceList(String id)
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);
			
			HttpGet httpGet = new HttpGet(uriBuilder.build());
		    
		    httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
		    
		    HttpResponse httpResponse = httpClient.execute(httpGet);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
			error.printStackTrace();
		    return error.getMessage();
		}
	}
	public static String deleteFaceList(String id)
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);
			
			HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
		    
		    httpDelete.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
		    
		    HttpResponse httpResponse = httpClient.execute(httpDelete);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
			error.printStackTrace();
		    return error.getMessage();
		}
	}
	public static String createFaceList(String id, String name)
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists/" + id);
			
			HttpPut httpPut = new HttpPut(uriBuilder.build());
		    
		    httpPut.setHeader("Content-Type", "application/json");
		    httpPut.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
		    
		    httpPut.setEntity(new StringEntity("{\"name\":\"" + name + "\"}"));
		    
		    HttpResponse httpResponse = httpClient.execute(httpPut);
			
			return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
			error.printStackTrace();
		    return error.getMessage();
		}
	}
	public static String getMultipleFaceList()
	{
		HttpClient httpClient = HttpClients.createDefault();
		
		try
		{			
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/facelists");
			
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			
		    httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_face_key);
		   
		    HttpResponse httpResponse = httpClient.execute(httpGet);
		    
		    return EntityUtils.toString(httpResponse.getEntity()).trim();
		}
		catch (Exception error)
		{
			error.printStackTrace();
		    return error.getMessage();
		}
	}
}
