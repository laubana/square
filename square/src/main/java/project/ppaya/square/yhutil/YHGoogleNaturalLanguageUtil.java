package project.ppaya.square.yhutil;

import java.util.ArrayList;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;

import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Token;

public class YHGoogleNaturalLanguageUtil
{
	public static void test1()
	{
		try(LanguageServiceClient language = LanguageServiceClient.create())
		{
			String text = "Hello, world!";
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
			System.out.printf("Text: %s%n", text);
			System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
		}
		catch(Exception error){error.printStackTrace();}
	}
	public static ArrayList<String> getPhraseList(String string)
	{
		ArrayList<String> phrase_list = new ArrayList<>();
		
		try(LanguageServiceClient language = LanguageServiceClient.create())
		{
			Document doc = Document.newBuilder().setContent(string).setType(Type.PLAIN_TEXT).build();
			AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc).setEncodingType(EncodingType.UTF16).build();
			AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
			for(Token token : response.getTokensList())
			{
				phrase_list.add(token.getText().getContent());
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return phrase_list;
	}
	public static ArrayList<String> getKeyPhraseList(String string, String language)
	{
		ArrayList<String> key_phrase_list = new ArrayList<>();
		
		try
		{
			LanguageServiceClient language_service_client = LanguageServiceClient.create();
					
			Document document = Document.newBuilder().setContent(string).setLanguage(language).setType(Type.PLAIN_TEXT).build();
			AnalyzeEntitiesRequest analyze_entitiesRequest = AnalyzeEntitiesRequest.newBuilder().setDocument(document).setEncodingType(EncodingType.UTF16).build();

			AnalyzeEntitiesResponse analyze_entitiesResponse = language_service_client.analyzeEntities(analyze_entitiesRequest);

			for(Entity entity : analyze_entitiesResponse.getEntitiesList())
			{
				key_phrase_list.add(entity.getName());
			}
		}
		catch(Exception error){error.printStackTrace();}
		
		return key_phrase_list;
	}
}
