package sk.upjs.Engine.Categorizers;

import sk.upjs.Engine.Tokenizers.Tokenizer;
import sk.upjs.Engine.Tokenizers.TokenizerFactory;
import sk.upjs.Engine.Translators.Translator;
import sk.upjs.Engine.Translators.TranslatorFactory;
import sk.upjs.Entities.Category;
import sk.upjs.Entities.DescribedEvent;
import sk.upjs.Entities.Event;
import sk.upjs.Entities.TokenedEvent;

import java.util.HashMap;

/**
 * Created by Viliam on 24.5.2015.
 */
public class TranslationTermCategorizer extends TranslationSimpleCategorizer {
    @Override
    public Category determineCategory(Event event) {
        return super.determineCategory(tokenize(event));
    }

    private Event tokenize(Event event) {
        if(event instanceof DescribedEvent) {
            Tokenizer tokenizer = TokenizerFactory.createTokenizer(TokenizerFactory.TokenizerType.TERM);
            return tokenizer.toTokenedEvent((DescribedEvent) event);
        }else {
            return event;
        }
    }

    public HashMap<Category,Integer> determineCategories(Event event){
        return super.determineCategories(tokenize(event));
    }
}
