package sk.upjs.Engine.Tokenizers;

import edu.stanford.nlp.tagger.maxent.Extractor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.Map;
import java.util.Scanner;

import static sk.upjs.Constants.STRING_DELIMITER;

/**
 * Created by Viliam on 4.4.2015.
 */

/**
 * Only nouns/terms should be counted aa tokens.
 */
public class TermTokenizer extends Tokenizer{


    public static final String TAGGER_DEFAULT_MODEL = "trainedModels\\wsj-0-18-caseless-left3words-distsim.tagger";

//    private Map<String, Integer> extractNouns(String taggedText) {
//
//    }

//    private boolean isNoun(String taggedWord) {
//        if(taggedWord.endsWith("_NN")||taggedWord.endsWith("_NNS")||taggedWord.endsWith("_NNP"))
//            return true;
//        return false;
//    }

    private String untagNoun(String taggedWord){
        if(taggedWord.endsWith("_NN"))
            return taggedWord.substring(0,taggedWord.length()-3);
        else if(taggedWord.endsWith("_NNP")||taggedWord.endsWith("_NNS"))
            return taggedWord.substring(0,taggedWord.length()-4);
        return null;
        //else if(taggedWord.endsWith(""))
    }

    private String tagText(String text){
        MaxentTagger tagger = new MaxentTagger(TAGGER_DEFAULT_MODEL);
        String taggedString = tagger.tagTokenizedString(text);
        return taggedString;
    }

    @Override
    protected void tokenize(String text, Map<String, Integer> map) {
        String taggedText = tagText(text);
        Scanner sc = new Scanner(taggedText);

        sc.useDelimiter(STRING_DELIMITER);
        sc.delimiter();
        while (sc.hasNext()) {
            String s = untagNoun(sc.next().toLowerCase());
            if(s==null) continue;
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else map.put(s, map.get(s) + 1);
        }
    }
}
