package sk.upjs.Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viliam on 8.5.2015.
 */
public class WeightedCategory extends Category {
    private List<Integer> weights;

    WeightedCategory(String categoryName) {
        super(categoryName);
        this.weights = new ArrayList<>();
        this.weights = new ArrayList<>();
    }

    public List<Integer> getWeights() {
        return weights;
    }
}
