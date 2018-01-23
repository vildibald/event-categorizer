package sk.upjs.Engine.Tokenizers;

/**
 * Created by Viliam on 19.5.2015.
 */
public class TokenizerFactory {

    public enum TokenizerType{
        SIMPLE(SimpleTokenizer.class),
        TERM(TermTokenizer.class);

        private Class<? extends Tokenizer> tokenizerClass;

        private TokenizerType(Class<? extends Tokenizer> tokenizerClass){
            this.tokenizerClass=tokenizerClass;
        }
        public Class<? extends Tokenizer> getTokenizerClass() {
            return this.tokenizerClass;
        }
    }

    public static Tokenizer createTokenizer(TokenizerType type){
        try {
            return type.getTokenizerClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Tokenizer createTokenizer(){
        return new TermTokenizer();
    }
}
