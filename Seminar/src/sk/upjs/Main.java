package sk.upjs;

import sk.upjs.Engine.JSONCollectionMapper;
import sk.upjs.Engine.Tokenizers.SimpleTokenizer;
import sk.upjs.Entities.DescribedEvent;
import sk.upjs.Entities.TokenedEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static sk.upjs.Constants.DEFAULT_SAMPLES;

public class Main {

    public static void main(String[] args) throws IOException {
        JSONCollectionMapper data = new JSONCollectionMapper();
        ArrayList<DescribedEvent> events = data.parseToCollection(DEFAULT_SAMPLES, ArrayList.class, DescribedEvent.class);
        //for (int i = 0; i < events.size(); i++) {
        //   System.out.println(events.get(i).getEid());
        //}
        SimpleTokenizer tokenizer = new SimpleTokenizer();
       // ArrayList<DescribedEvent> describedEvents = tokenizer.tokenize(events);
        List<TokenedEvent> tokenedEvents = tokenizer.toTokenedEvents(events);

        System.out.println("Done.");
    }

   public static <E> void println(Collection<E> collection){
       for (E item : collection){

       }
   }
}
