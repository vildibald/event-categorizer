package sk.upjs.Engine.Categorizers;


/**
 * Created by Viliam on 6.5.2015.
 */
public class CategorizerFactory {
    public enum CategorizerType{
        SIMPLE(SimpleCategorizer.class),
        TRANSLATION_SIMPLE(TranslationSimpleCategorizer.class),
        WEIGHTED(WeightedCategorizer.class),
        TRANSLATION_TERM(TranslationTermCategorizer.class);

        private Class<? extends Categorizer> categorizerClass;

        private CategorizerType(Class<? extends Categorizer> categorizerClass){
            this.categorizerClass=categorizerClass;
        }
        public Class<? extends Categorizer> getCategorizerClass() {
            return this.categorizerClass;
        }
    }

    public static Categorizer createCategorizer(CategorizerType type){
        try {
            return type.getCategorizerClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Categorizer createCategorizer(){
        return new SimpleCategorizer();
    }

//    public static Categorizer createTranslationCategorizer(){
//        return new SimpleCategorizer();
//    }
}
