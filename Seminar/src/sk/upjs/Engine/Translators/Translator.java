package sk.upjs.Engine.Translators;

import sk.upjs.Entities.DescribedEvent;
import sk.upjs.Entities.Event;
import sk.upjs.Entities.TokenedEvent;

import javax.activation.UnsupportedDataTypeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Viliam on 5.5.2015.
 */

public abstract class Translator {

    public Translator(Language toLanguage) {
    }

    public abstract String translate(String text) throws IOException;

    public Event translate(Event event) throws IOException{
        if(event instanceof DescribedEvent){
            return translate((DescribedEvent)event);
        }else if(event instanceof TokenedEvent){
            return translate((TokenedEvent) event);
        }else{
            throw new UnsupportedDataTypeException("Event must be instance of DescribedEvent" +
                    "or TokenedEvent.");
        }
    }

    public DescribedEvent translate(DescribedEvent event) throws IOException{
        String trName = translate(event.getName());
        String trDescription = translate(event.getDescription());
        DescribedEvent trEvent = new DescribedEvent(event);
        trEvent.setDescription(trDescription);
        trEvent.setName(trName);
        return trEvent;
    }

    public TokenedEvent translate(TokenedEvent event) throws IOException{
        String trName = translate(event.getName());
        Map<String, Integer> trMap = new HashMap<>(event.getCountedTokens().size());
        event.forEachToken((item)->{
            try {
                String trItem = translate(item.getKey());
                trMap.put(trItem,item.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        TokenedEvent trEvent = new TokenedEvent(event, trMap);
       // trEvent.set(trDescription);
        trEvent.setName(trName);
        return trEvent;
    }


}
