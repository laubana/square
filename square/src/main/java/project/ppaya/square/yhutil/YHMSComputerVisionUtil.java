package project.ppaya.square.yhutil;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import project.ppaya.square.vo.*;

public class YHMSComputerVisionUtil
{	
	public static ArrayList<String> getTagList(String path, String file, String language) 
	{		
		ArrayList<String> tag_list = new ArrayList<>();
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/tag");

			uriBuilder.setParameter("language", language);
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/octet-stream");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_computer_vision_key);

			httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			String result = EntityUtils.toString(httpResponse.getEntity()).trim();
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("tags");
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				tag_list.add(jsonArray.getJSONObject(i).getString("name"));
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return tag_list;
	}
	public static ArrayList<String> getDescriptionList(String path, String file, String language) 
	{		
		ArrayList<String> description_list = new ArrayList<>();
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/describe");

			uriBuilder.setParameter("language", language);
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/octet-stream");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_computer_vision_key);

			httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			String result = EntityUtils.toString(httpResponse.getEntity()).trim();
			
			JSONObject jsonObject = new JSONObject(result);
			jsonObject = jsonObject.getJSONObject("description");
			JSONArray jsonArray = jsonObject.getJSONArray("captions");
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				description_list.add(jsonArray.getJSONObject(i).getString("text"));
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return description_list;
	}
	public static ArrayList<String> getBrandList(String path, String file, String language) 
	{		
		ArrayList<String> brand_list = new ArrayList<>();
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze");

			uriBuilder.setParameter("visualFeatures", "Brands");
			uriBuilder.setParameter("language", language);
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/octet-stream");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_computer_vision_key);

			httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			String result = EntityUtils.toString(httpResponse.getEntity()).trim();
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("brands");
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				brand_list.add(jsonArray.getJSONObject(i).getString("name"));
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return brand_list;
	}
	public static ArrayList<String> getCategoryList(String path, String file, String language) 
	{		
		ArrayList<String> category_list = new ArrayList<>();
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/analyze");

			uriBuilder.setParameter("visualFeatures", "Categories");
			uriBuilder.setParameter("language", language);
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/octet-stream");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_computer_vision_key);

			httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			String result = EntityUtils.toString(httpResponse.getEntity()).trim();
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("categories");
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				category_list.add(jsonArray.getJSONObject(i).getString("name"));
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return category_list;
	}
	public static ArrayList<ArrayList<String>> setTextList(String path, String file, String mode) 
	{		
		ArrayList<ArrayList<String>> text_list_list = new ArrayList<>();
		String result = null;
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v2.0/read/core/asyncBatchAnalyze");

			uriBuilder.setParameter("mode", mode);
			
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/octet-stream");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_computer_vision_key);

			httpPost.setEntity(new FileEntity(new File(path + "\\" + file)));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			 
			Header[] headers = httpResponse.getAllHeaders();			 	
			for(Header header : headers)
			{
				if(header.getName().equals("Operation-Location"))
				{
					result = header.getValue();
					 
					break;
				}
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		try{Thread.sleep(10000);}catch(Exception error){}
		
		httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder(result);
			
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_computer_vision_key);

			HttpResponse httpResponse = httpClient.execute(httpGet);
			 
			result = EntityUtils.toString(httpResponse.getEntity()).trim();
						
			JSONObject jsonObject = new JSONObject(result);			
			JSONArray jsonArray = jsonObject.getJSONArray("recognitionResults");
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				jsonObject = jsonArray.getJSONObject(i);
				
				JSONArray lineArray = jsonObject.getJSONArray("lines");
				
				ArrayList<String> text_list = new ArrayList<>();
				
				for(int j = 0; j < lineArray.length(); j++)
				{
					JSONObject lineObject = lineArray.getJSONObject(j);
					
					text_list.add(lineObject.getString("text"));
				}
				
				text_list_list.add(text_list);
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return text_list_list;
	}
}
