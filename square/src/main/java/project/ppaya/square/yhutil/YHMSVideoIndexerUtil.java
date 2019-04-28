package project.ppaya.square.yhutil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import project.ppaya.square.vo.*;
import project.ppaya.square.yhdao.*;

public class YHMSVideoIndexerUtil
{	
	public static int delay = 0;
	public static String getThumbnail(String video_id, String thumbnail_id)
	{
		HttpClient httpClient = HttpClients.createDefault();
		String result;
		
        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/" + Reference.ms_video_indexer_id + "/Videos/" + video_id + "/Thumbnails/" + thumbnail_id);

            uriBuilder.setParameter("format", "Base64");
            uriBuilder.setParameter("accessToken", getAccessToken());

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            
            while(true)
            {

                HttpResponse httpResponse = httpClient.execute(httpGet);
                result = EntityUtils.toString(httpResponse.getEntity()).trim();
                
            	System.err.println(result);
            	
            	if(result.charAt(0) == '{' || result.charAt(0) == '[')
            	{
            		Thread.sleep(delay);
            		continue;
            	}
            	else
            	{                    
                    return result;
            	}
            }
        }
        catch(Exception error){error.printStackTrace(); return null;}
	}
	public static String getAccessToken()
	{		
		HttpClient httpClient = HttpClients.createDefault();
		String result;
        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/auth/trial/Accounts/" + Reference.ms_video_indexer_id + "/AccessToken");

            uriBuilder.setParameter("allowEdit", "true");

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.ms_video_indexer_key);

            while(true)
            {
            	HttpResponse httpResponse = httpClient.execute(httpGet);            
            	result = EntityUtils.toString(httpResponse.getEntity()).trim();
            	
            	System.err.println(result);
            	
            	if(result.charAt(0) == '{' || result.charAt(0) == '[')
            	{
            		Thread.sleep(delay);
            		continue;
            	}
            	else
            	{                    
                    return result.replaceAll("\"", "");
            	}
            }
        }
        catch(Exception error){error.printStackTrace(); return null;}
	}
	public static String getVideoIndex(String video_id) 
    {	
        HttpClient httpClient = HttpClients.createDefault();
        String result;

        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/" + Reference.ms_video_indexer_id + "/Videos/" + video_id + "/Index");
            uriBuilder.setParameter("accessToken", getAccessToken());

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if(entity != null)
            {
            	result = EntityUtils.toString(entity).trim();
            	
            	System.err.println(result);
            	
            	JSONObject jsonObject = new JSONObject(result);
            	return jsonObject.toString(2);
            }
            else{return null;}
        }
        catch (Exception error){error.printStackTrace(); return null;}
    }
}
