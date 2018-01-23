package sk.upjs.Engine.Tokenizers;


import java.util.*;

import static sk.upjs.Constants.*;

/**
 * Created by Viliam on 22.3.2015.
 */

/**
 * Naive tokenizer, where each word in Described event is considered as token.
 */
public class SimpleTokenizer extends Tokenizer {


    protected void tokenize(String text, final Map<String, Integer> map) {
        Scanner sc = new Scanner(text);
        sc.useDelimiter(STRING_DELIMITER);
        sc.delimiter();
        while (sc.hasNext()) {
            String s = sc.next().toLowerCase();

            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
    }


}
