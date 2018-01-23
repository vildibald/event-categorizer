package sk.upjs.Engine.Translators;

/**
 * Created by Viliam on 6.5.2015.
 */
public class TranslatorFactory {

    public static Translator createTranslator(Language toLanguage){
        return new YandexTranslator(toLanguage);
    }
}
