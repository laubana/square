package project.ppaya.square.yhutil;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class YHGoogleTranslationUtil
{
	public static String getTranslation(String string, String source_language, String target_language)
	{
		Translate translate = TranslateOptions.getDefaultInstance().getService();
		Translation translation = translate.translate(string, TranslateOption.sourceLanguage(source_language), TranslateOption.targetLanguage(target_language));
	
		return translation.getTranslatedText();
	}
}