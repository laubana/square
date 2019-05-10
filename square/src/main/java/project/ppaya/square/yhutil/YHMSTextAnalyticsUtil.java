package project.ppaya.square.yhutil;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import project.ppaya.square.vo.*;

public class YHMSTextAnalyticsUtil
{	
	public static ArrayList<String> getKeyPhraseList(String string, String language) 
	{		
		ArrayList<String> key_phrase_list = new ArrayList<>();
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://" + Reference.azure_text_analytics_region + ".api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases");

			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_text_analytics_key);

			StringEntity stringEntity = new StringEntity(
					"{"
					+ "\"documents\":"
					+ "["
					+ "{"
					+ "\"language\":\"" + language + "\","
					+ "\"id\":\"1\","
					+ "\"text\":\"" + string + "\""
					+ "}"
					+ "]"
					+ "}"
					);
			httpPost.setEntity(stringEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			String result = EntityUtils.toString(httpResponse.getEntity()).trim();
			
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("documents");
			jsonObject = jsonArray.getJSONObject(0);
			jsonArray = jsonObject.getJSONArray("keyPhrases");
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
				key_phrase_list.add(jsonArray.getString(i));
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return key_phrase_list;
	}
	public static double getSentiment(String string, String language) 
	{		
		double sentiment = -1;
		
		HttpClient httpClient = HttpClients.createDefault();

		try
		{
			URIBuilder uriBuilder = new URIBuilder("https://" + Reference.azure_text_analytics_region + ".api.cognitive.microsoft.com/text/analytics/v2.0/sentiment");

			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Ocp-Apim-Subscription-Key", Reference.azure_text_analytics_key);

			StringEntity stringEntity = new StringEntity(
					"{"
					+ "\"documents\":"
					+ "["
					+ "{"
					+ "\"language\":\"" + language + "\","
					+ "\"id\":\"1\","
					+ "\"text\":\"" + string + "\""
					+ "}"
					+ "]"
					+ "}"
					);
			httpPost.setEntity(stringEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			String result = EntityUtils.toString(httpResponse.getEntity()).trim();
			
			JSONObject jsonObject = new JSONObject(result);
			
			JSONArray jsonArray = jsonObject.getJSONArray("documents");
			jsonObject = jsonArray.getJSONObject(0);
			sentiment = jsonObject.getDouble("score");
		}
		catch(Exception error){error.printStackTrace();}
		
		return sentiment;
	}
}
