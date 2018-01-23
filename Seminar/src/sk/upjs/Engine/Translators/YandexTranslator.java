package sk.upjs.Engine.Translators;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Viliam on 5.5.2015.
 */
public class YandexTranslator extends Translator {
    private static final String API_KEY = "trnsl.1.1.20150503T111000Z.9f13b1370c6e5259.0847127c8f85934dc0ef6cf2f678e2bb21df81a1";
    private static final String API_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+ API_KEY;
    private String url;

    public YandexTranslator(Language toLanguage){
        super(toLanguage);
        switch (toLanguage){
            case SLOVAK:
                url = API_URL+"&lang=sk";
                break;
            case CZECH:
                url = API_URL+"&lang=cs";
                break;
            case FRENCH:
                url = API_URL+"&lang=fr";
                break;
            case GERMAN:
                url = API_URL+"&lang=de";
                break;
            case SPANISH:
                url = API_URL+"&lang=es";
                break;
            default:
                url = API_URL+"&lang=en";
                break;
        }
    }

    @Override
    public String translate(String text) throws IOException{
            StringBuilder resultBuilder = new StringBuilder();
           // String translResult = "";
        //+"&lang=en";

        final String translateUrl;

        translateUrl = url + "&text="+ URLEncoder.encode(text, "utf-8");

        System.out.println(url);

            URL translUrl = new URL(translateUrl);
            URLConnection conn = translUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                resultBuilder.append(inputLine);
            in.close();

            String jsonResult = resultBuilder.toString();
            ObjectMapper mapper = new ObjectMapper();
            mapper.getTypeFactory();
            TranslateResult result = mapper.readValue(jsonResult, TranslateResult.class);
            return result.getText()[0];
           // JSONObject jsonObj = new JSONObject(translResult);

           // return jsonObj.getJSONArray("text").get(0).toString();

    }

     private static class TranslateResult{
        private int code;
        private String lang;
        private String[] text;

         public TranslateResult(){

         }

         public void setCode(int code) {
             this.code = code;
         }

         public void setLang(String lang) {
             this.lang = lang;
         }

         public void setText(String[] text) {
             this.text = text;
         }

         public int getCode() {
             return code;
         }

         public String getLang() {
             return lang;
         }


         public String[] getText() {
             return text;
         }


     }
}
