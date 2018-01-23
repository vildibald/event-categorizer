package sk.upjs.Entities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Viliam on 22.3.2015.
 */

/**
 * Event, where each token/term and count of occurrences is hold in map.
 * To retrieve an instance from DescribedEvent use Tokenizer class
 */
public class TokenedEvent extends Event {
    private Map<String, Integer> countedTokens;
    private int tokensCount = -1;
    private Map<String, Integer> countedTokens1;

    public TokenedEvent(Event event, Map<String, Integer> tokensCounted) {
        super(event.getEid(), event.getName(), event.getStartTime(), event.getEndTime(), event.getEndTimeComputed(), event.getCreator(), event.getCreatorCategory(), event.getLocation(), event.getLocationFilter(), event.getLatitude(), event.getLongitude(), event.getHost(), event.getPrivacy(), event.getType(), event.isValid());
        this.countedTokens = tokensCounted;
    }

    public TokenedEvent(TokenedEvent event) {
        super(event);
        countedTokens = countedTokens1;
        tokensCount = event.getTokensCount();
    }

    public Map<String, Integer> getCountedTokens() {
        return countedTokens;
    }

    public int getTokensCount() {
        if (tokensCount == -1) {
            int count = 0;
            // To nemohli do tej JAVY 8 dat proste metodu SUM pre kolekcie?!
            // Zlate LINQ....
            //countedTokens.values().stream()
            Iterator it = countedTokens.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                count += ((Integer) pair.getValue()).intValue();
                //it.remove(); // avoids a ConcurrentModificationException
            }

            tokensCount = count;
            return count;
        } else {
            return tokensCount;
        }
    }

    @Override
    public int occurencesCount(String word) {
        int count = super.occurencesCount(word);
        Iterator it = countedTokens.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry) it.next();
            if (word.equals(pair.getKey()))
                count += pair.getValue();
            else if(pair.getKey().contains(word))
                count+=pair.getValue();
        }
//        forEachToken((pair)->{
//            if (word.equals(pair.getKey()))
//                count += pair.getValue();
//            else if(pair.getKey().contains(word))
//                count+=pair.getValue();
//        });
        //count+= countedTokens.getOrDefault(word,0);

        return count;
    }

    /**
     * Iteration through token map.
     *
     * @param action Action performed for each token.
     */
    public void forEachToken(Consumer<Map.Entry<String, Integer>> action) {
        Iterator it = countedTokens.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry) it.next();
            action.accept(pair);
        }
    }
}
