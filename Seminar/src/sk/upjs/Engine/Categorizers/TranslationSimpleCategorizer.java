package sk.upjs.Engine.Categorizers;

import sk.upjs.Engine.Translators.Language;
import sk.upjs.Engine.Translators.Translator;
import sk.upjs.Engine.Translators.TranslatorFactory;
import sk.upjs.Entities.Category;
import sk.upjs.Entities.Event;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Viliam on 6.5.2015.
 */
public class TranslationSimpleCategorizer extends SimpleCategorizer {
    private Language toLanguage;

    public TranslationSimpleCategorizer(Language toLanguage) {
        this.toLanguage = toLanguage;
    }
    public TranslationSimpleCategorizer() {
        this(Language.ENGLISH);
    }

    @Override
    public Category determineCategory(Event event) {
        Event translatedEvent = translate(event);
        //if (translatedEvent==null) return null;
        return super.determineCategory(translatedEvent);
    }



    @Override
    public HashMap<Category,Integer> determineCategories(Event event){
        Event translatedEvent = translate(event);
        //if (translatedEvent==null) return null;
        return super.determineCategories(translatedEvent);
    }

    private Event translate(Event event) {
        Translator translator = TranslatorFactory.createTranslator(toLanguage);
        Event translatedEvent = null;
        try {
            translatedEvent = translator.translate(event);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return translatedEvent;
        }

    }
}
