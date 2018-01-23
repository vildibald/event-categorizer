package sk.upjs.Engine.Tokenizers;

import sk.upjs.Entities.DescribedEvent;
import sk.upjs.Entities.TokenedEvent;
import sk.upjs.Utilities.CollectionTransformer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Viliam on 4.4.2015.
 */
public abstract class Tokenizer {
    /**
     * Retrieve TokenedEvent from DescribedEvent
     * @param describedEvents
     * @return
     */
    public List<TokenedEvent> toTokenedEvents(List<DescribedEvent> describedEvents){

        CollectionTransformer transformer = new CollectionTransformer<DescribedEvent,TokenedEvent>() {
            @Override
            public TokenedEvent transform(DescribedEvent describedEvent){
                return toTokenedEvent(describedEvent);
            }
        };
        return transformer.transform(describedEvents);
    }

    /**
     *
     * @param describedEvent
     * @return
     */
    public TokenedEvent toTokenedEvent(DescribedEvent describedEvent){
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        tokenize(describedEvent.getDescription(), map);
        tokenize(describedEvent.getName(), map);
        tokenize(describedEvent.getLocation(), map);
        return new TokenedEvent(describedEvent,map);
    }

    /**
     * How should be tokens retrieved from text.
     * @param text Source text.
     * @param map Map to save each token with number of it's occurrences.
     */
    protected abstract void tokenize(String text, final Map<String, Integer> map);
}
