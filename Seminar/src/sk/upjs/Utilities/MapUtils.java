package sk.upjs.Utilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Viliam on 23.5.2015.
 */
public class MapUtils {
    public static <K> K largestValuedKey(HashMap<K,Integer> map){
        if(map==null) return null;
        Iterator it = map.entrySet().iterator();
        int max=Integer.MIN_VALUE;
        K maxKey =null;
        while (it.hasNext()) {
            Map.Entry<K, Integer> pair = (Map.Entry) it.next();
            int pairValue=pair.getValue().intValue();
            if(max<pairValue){
                max = pairValue;
                maxKey = pair.getKey();
            }
        }
        return maxKey;
    }

    public static <K> K secondLargestValuedKey(HashMap<K,Integer> map){
        if(map==null) return null;
        Iterator it = map.entrySet().iterator();
        int max=Integer.MIN_VALUE,smax=Integer.MIN_VALUE;
        K maxKey =null,smaxKey =null;
        while (it.hasNext()) {
            Map.Entry<K, Integer> pair = (Map.Entry) it.next();
            int pairValue=pair.getValue().intValue();
            if(max<pairValue){
                smax=max;
                max = pairValue;
                smaxKey=maxKey;
                maxKey = pair.getKey();
            }else if(smax<pairValue){
                smax=pairValue;
                smaxKey = pair.getKey();
            }
        }
        if(map.size()==1) return maxKey;
        return smaxKey;
    }
}
