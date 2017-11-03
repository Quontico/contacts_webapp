package util;

import java.util.regex.*;

public class ValidateInput {
    public static boolean testFieldValue(String regexString, String strFieldInput) {
        if ((regexString != null) && (regexString.length() > 0)) {
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(strFieldInput);
            if (!matcher.find()) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
