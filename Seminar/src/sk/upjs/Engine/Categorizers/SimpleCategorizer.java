package sk.upjs.Engine.Categorizers;

import sk.upjs.Entities.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Viliam on 26.4.2015.
 */
public class SimpleCategorizer implements Categorizer {




    @Override
    public Category determineCategory(Event event) {
        if(event==null) return null;
        Categories categories = Categories.getInstance();
        Category bestMatched = null;
        int maxMatches = 0;
        for(Category category : categories){
            int matches = calculateKeywordsMatches(category.getFeatureWords(), event);
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
        if(event==null) return null;
        Categories categories = Categories.getInstance();
        //Category bestMatched = null;
        HashMap<Category,Integer> map = new HashMap<>(categories.size());
        for(Category category : categories){
            int matches = calculateKeywordsMatches(category.getFeatureWords(), event);
            if(matches<1)continue;
            map.put(category,matches);
        }
//        if(bestMatched ==null)
//            event.setType("Other");
//        else
//            event.setType(bestMatched.getName());
        return map;
    }

    private int calculateKeywordsMatches(List<String> keywords, Event event) {
        int count=0;
        for (String keyword : keywords){
          count+=event.occurencesCount(keyword);
        }
        return count;
    }


}
