package sk.upjs.Engine.Categorizers;


import sk.upjs.Entities.Category;
import sk.upjs.Entities.Event;

import java.util.HashMap;

/**
 * Created by Viliam on 26.4.2015.
 */
public interface Categorizer {
    /**
     *
     * @param event
     * @return
     */
    public Category determineCategory(Event event);
    public HashMap<Category,Integer> determineCategories(Event event);
}
