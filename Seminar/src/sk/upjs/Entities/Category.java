package sk.upjs.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viliam on 7.4.2015.
 */
// Trieda predstavujucia novu kategoriu
// V programe by instancie nemali byt vytvarane, jedine nacitane zo suboru
// Momentalne sa trieda v programe nepouziva
public class Category implements Comparable<Category> {
    //private int id;
    private String categoryName;
    private List<String> featureWords;

    Category(String categoryName) {
        this.categoryName = categoryName;
        this.featureWords = new ArrayList<>();
        //this.id = categoryName.hashCode();
    }

    public List<String> getFeatureWords() {
        return featureWords;
    }

    public String getName() {
        return categoryName;
    }

    @Override
    public int compareTo(Category o) {
        return this.categoryName.compareTo(o.getName());
    }

}
