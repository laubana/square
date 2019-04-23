package project.ppaya.square.yhutil;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import project.ppaya.square.vo.*;

public class YHMSVideoIndexerUtil
{
	public static String getThumbnail(String video_id, String thumbnail_id)
	{
		HttpClient httpClient = HttpClients.createDefault();

        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/" + Reference.ms_video_indexer_id + "/Videos/" + video_id + "/Thumbnails/" + thumbnail_id);

            uriBuilder.setParameter("format", "Base64");
            uriBuilder.setParameter("accessToken", getAccessToken());

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            String result = EntityUtils.toString(httpEntity).trim();
            
            if(httpEntity != null){return result;}
            else{return null;}
        }
        catch(Exception error){error.printStackTrace(); return null;}
	}
	public static String getAccessToken()
	{		
		HttpClient httpClient = HttpClients.createDefault();
		
        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/auth/trial/Accounts/" + Reference.ms_video_indexer_id + "/AccessToken");

            uriBuilder.setParameter("allowEdit", "true");

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Ocp-Apim-Subscription-Key", Reference.ms_video_indexer_key);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            
            HttpEntity httpEntity = httpResponse.getEntity();

            if(httpEntity != null){return EntityUtils.toString(httpEntity).replaceAll("\"", "");}
            else{return null;}
        }
        catch(Exception error){error.printStackTrace(); return "";}
	}
	public static String uploadVideo(String path, String filename)
	{
		HttpClient httpClient = HttpClients.createDefault();

        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/"+ Reference.ms_video_indexer_id +"/Videos?name=" + filename + "&accessToken=" + getAccessToken());

            HttpPost httpPost = new HttpPost(uriBuilder.build());
	        
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            
            multipartEntityBuilder.addBinaryBody("film2", new File(path + "\\" + filename));
            
            HttpEntity httpEntity = multipartEntityBuilder.build();
            
            httpPost.setEntity(httpEntity);

            HttpResponse response = httpClient.execute(httpPost);
            httpEntity = response.getEntity();

            if(httpEntity != null)
            {
            	JSONObject jsonObject = new JSONObject(EntityUtils.toString(httpEntity).trim());
            	return jsonObject.getString("id");
            }
            else{return null;}
        }
        catch(Exception error){error.printStackTrace(); return null;}
	}
	public static String getVideoIndex(String video_id) 
    {
        HttpClient httpClient = HttpClients.createDefault();

        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/" + Reference.ms_video_indexer_id + "/Videos/" + video_id + "/Index");
            uriBuilder.setParameter("accessToken", getAccessToken());

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if(entity != null)
            {
            	JSONObject jsonObject = new JSONObject(EntityUtils.toString(entity).trim());
            	return jsonObject.toString(2);
            }
            else{return null;}
        }
        catch (Exception error){error.printStackTrace(); return null;}
    }
	public static void createPersonModel(String name) 
    {
        HttpClient httpClient = HttpClients.createDefault();

        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/" + Reference.ms_video_indexer_id + "/Customization/PersonModels?name=" + name + "&accessToken=" + getAccessToken());

            HttpPost request = new HttpPost(uriBuilder.build());

            StringEntity reqEntity = new StringEntity("{body}");
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
