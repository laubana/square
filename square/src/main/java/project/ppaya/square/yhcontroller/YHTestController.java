package project.ppaya.square.yhcontroller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.ppaya.square.vo.Group;
import project.ppaya.square.vo.Reference;
import project.ppaya.square.yhdao.YHGroupAttendanceDAO;
import project.ppaya.square.yhdao.YHGroupDAO;
import project.ppaya.square.yhutil.YHUserFormUtil;

/**
 * Handles requests for the application home page.
 */
@Repository
@Controller
public class YHTestController
{	
	private static final Logger logger = LoggerFactory.getLogger(YHTestController.class);
	
	@Autowired
	YHUserFormUtil yh_user_formUtil;
	
	@Autowired
	YHGroupDAO yh_groupDAO;
	@Autowired
	YHGroupAttendanceDAO yh_group_attendanceDAO;
	
	@RequestMapping(value = "yhtest", method = RequestMethod.GET)
	public void test()
	{
		String accessToken = null;
		
		HttpClient httpClient = HttpClients.createDefault();
		
        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/auth/trial/Accounts/0180a9ea-086b-49b0-80db-559c99812a47/AccessToken");

            uriBuilder.setParameter("allowEdit", "true");

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Ocp-Apim-Subscription-Key", "5d4f078a8c764dd8a89d69c3480e77ec");

            HttpResponse httpResponse = httpClient.execute(httpGet);
            
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null) 
            {
            	accessToken = EntityUtils.toString(httpEntity);
            	System.out.println(accessToken);
            	System.out.println(accessToken.replaceAll("\"", ""));
            	
            }
        }
        catch(Exception error){error.printStackTrace();}
        
		httpClient = HttpClients.createDefault();

        try
        {
            URIBuilder uriBuilder = new URIBuilder("https://api.videoindexer.ai/trial/Accounts/"+ Reference.ms_video_index_id +"/Videos?name=" + (new Date()).getTime() + "&accessToken=" + accessToken + "\"");

            uriBuilder.setParameter("privacy", "Private");
            uriBuilder.setParameter("priority", "{string}");
            uriBuilder.setParameter("description", "{string}");
            uriBuilder.setParameter("partition", "{string}");
            uriBuilder.setParameter("externalId", "{string}");
            uriBuilder.setParameter("externalUrl", "{string}");
            uriBuilder.setParameter("callbackUrl", "{string}");
            uriBuilder.setParameter("metadata", "{string}");
            uriBuilder.setParameter("language", "{string}");
            uriBuilder.setParameter("videoUrl", "{string}");
            uriBuilder.setParameter("fileName", "{string}");
            uriBuilder.setParameter("indexingPreset", "Default");
            uriBuilder.setParameter("streamingPreset", "Default");
            uriBuilder.setParameter("linguisticModelId", "{string}");
            uriBuilder.setParameter("personModelId", "{string}");
            uriBuilder.setParameter("sendSuccessEmail", "False");
            uriBuilder.setParameter("assetId", "{string}");
            uriBuilder.setParameter("brandsCategories", "{string}");

            HttpPost httpPost = new HttpPost(uriBuilder.build());
            
            httpPost.setHeader("Content-Type", "multipart/form-data");

            StringEntity reqEntity = new StringEntity("{body}");
            httpPost.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();

            if (httpEntity != null) 
            {
                System.out.println(EntityUtils.toString(httpEntity));
            }
        }
        catch(Exception error)
        {
        	error.printStackTrace();
        }
	}
}
