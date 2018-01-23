package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sk.upjs.Engine.Translators.Language;
import sk.upjs.Engine.Translators.YandexTranslator;

import static org.junit.Assert.*;

public class YandexTranslatorTest {

    private YandexTranslator translator;
    private String translated;

    @Before
    public void setUp() throws Exception {
        translator = new YandexTranslator(Language.ENGLISH);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println(translated);
    }

    @Test
    public void testTranslate() throws Exception {
       translated = translator.translate("Nad Tatrou sa blýska, hromy divo bijú.");
    }
}