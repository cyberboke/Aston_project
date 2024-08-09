package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Validator(){}

    public static boolean isValidData(String data, DataType dataType) {
        Pattern pattern = Pattern.compile(dataType.getPattern());
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }


}
