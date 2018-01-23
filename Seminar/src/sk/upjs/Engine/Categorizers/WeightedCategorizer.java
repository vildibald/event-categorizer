package sk.upjs.Engine.Categorizers;

import sk.upjs.Entities.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Viliam on 8.5.2015.
 */
public class WeightedCategorizer implements Categorizer {
    @Override
    public Category determineCategory(Event event) {
        WeightedCategories categories = WeightedCategories.getInstance();
        WeightedCategory bestMatched = null;
        int maxMatches = 0;
        for(WeightedCategory category : categories){
            int matches = calculateKeywordsMatches(category.getFeatureWords(),category.getWeights(), event);
            if(matches> maxMatches){
                maxMatches = matches;
                bestMatched = category;
            }
        }
//        if(bestMatched ==null)
//            event.setType("Other");
//        else
//            event.setType(bestMatched.getName());
        return bestMatched;
    }

    @Override
    public HashMap<Category, Integer> determineCategories(Event event) {
        return null;
    }

    // Vrati match keywordov jednej kategorie a eventu.
    private int calculateKeywordsMatches(List<String> keywords,List<Integer> weights,  Event event) {
        return 0;
    }
}
