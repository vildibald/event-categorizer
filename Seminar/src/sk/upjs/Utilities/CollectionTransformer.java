package sk.upjs.Utilities;

import sk.upjs.Entities.DescribedEvent;
import sk.upjs.Entities.TokenedEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Viliam on 25.3.2015.
 */
public abstract class CollectionTransformer<E, F> {

    public abstract F transform(E e);

    public List<F> transform(List<E> list) {
        List<F> newList;
        int len = list.size();
//        if(list instanceof Vector){
//            newList = new Vector<F>();
//        }else if(list instanceof LinkedList){
//            newList = new LinkedList<F>();
//        }

        newList = new ArrayList<F>(len);
        for (E e : list) {
            newList.add(transform(e));
        }
        return newList;
    }

}
