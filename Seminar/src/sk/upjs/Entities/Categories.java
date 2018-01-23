package sk.upjs.Entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Viliam on 7.4.2015.
 */
// List kategorii (singleton), predstavuje vsetky pouzivane kategorie a ich klucove slova
public class Categories extends ArrayList<Category> {

    private final List<ArrayList<String>> similarCategories = new ArrayList<>(5);
    private final Object lock = new Object();
    private static class Holder{
        public static Categories catInst = new Categories();
    }

    private Categories(){
        super(10);
    }

    public boolean similarity(Category category1, Category category2) {
        return areSimilar(category1.getName(), category2.getName());
    }

    public boolean areSimilar(String category1Name, String category2Name) {

        if (category1Name.equals(category2Name)) return true;
        for(List<String> listOfSimilarCategories : similarCategories){
            boolean category1Found=false, category2Found=false;
            for(String category : listOfSimilarCategories){
                if(category.equals(category1Name)) category1Found=true;
                if(category.equals(category2Name)) category2Found=true;
            }
            if(category1Found&&category2Found)
                return true;
        }
        return false;
    }

    @Override
    public boolean add(Category category) {
        synchronized (lock) {
            return super.add(category);
        }
    }

    @Override
    public boolean addAll(Collection<? extends Category> c) {
        synchronized (lock) {
            return super.addAll(c);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends Category> c) {
        synchronized (lock) {
            return super.addAll(index, c);
        }
    }

    public static Categories getInstance(){
        return Holder.catInst;
    }

    public static void loadFromFile(){
        loadFromFile(new File("categories.txt"));
    }

    public static void loadSimilaritiesFromFile(){loadSimilaritiesFromFile(new File("similarities.txt")); }

    public static void loadSimilaritiesFromFile(File file){
        try(Scanner sc = new Scanner(file)){

            ArrayList<String> cl = null;
            List<ArrayList<String>> scl = getInstance().similarCategories;
            //char frts = s.charAt(0);

            //if(s.charAt(1)==':' ||s.charAt(0)==':'){
            //    cat = new Category(s.substring(1));
            //    cl.add(cat);
            //}

            while (sc.hasNextLine()){
                String s = sc.nextLine();

                if(s.charAt(0)=='#') continue; // poznamka
                if(s.charAt(0)==';'){
                    cl = new ArrayList<String>(5);
                    scl.add(cl);
                }else {
                    cl.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile(File file){
        try(Scanner sc = new Scanner(file)){
            //sc.useDelimiter("\\n");
           // String s;
            //s = sc.nextLine();
            Category cat = null;
            Categories cl = getInstance();
            //char frts = s.charAt(0);

            //if(s.charAt(1)==':' ||s.charAt(0)==':'){
            //    cat = new Category(s.substring(1));
            //    cl.add(cat);
            //}

            while (sc.hasNextLine()){
                String s = sc.nextLine();

                if(s.charAt(0)=='#') continue; // poznamka
                if(s.charAt(0)==':'){
                    cat = new Category(s.substring(1));
                    cl.add(cat);
                }else {
                    cat.getFeatureWords().add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
