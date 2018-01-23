package sk.upjs.Utilities;

import java.util.regex.Pattern;

/**
 * Created by Viliam on 15.5.2015.
 */
public class RegexUtils {
    public static Pattern compilePattern(String regex){
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    }
}
