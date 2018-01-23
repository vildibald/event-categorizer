package sk.upjs.Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by Viliam on 8.5.2015.
 */
public class WeightedCategories extends ArrayList<WeightedCategory> {

    private final Object lock = new Object();
    private static class Holder{
        public static WeightedCategories catInst = new WeightedCategories();
    }

    private WeightedCategories(){
        super(10);
    }

    @Override
    public boolean add(WeightedCategory category) {
        synchronized (lock) {
            return super.add(category);
        }
    }

    @Override
    public boolean addAll(Collection<? extends WeightedCategory> c) {
        synchronized (lock) {
            return super.addAll(c);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends WeightedCategory> c) {
        synchronized (lock) {
            return super.addAll(index, c);
        }
    }

    public static WeightedCategories getInstance(){
        return Holder.catInst;
    }

    public static void loadFromFile(){
        loadFromFile(new File("wieghtedCategories.txt"));
    }

    public static void loadFromFile(File file){
     // Naparsovat WeightedCategory-ie zo suboru
    }
}
