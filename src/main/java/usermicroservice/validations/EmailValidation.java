package usermicroservice.validations;

import java.util.regex.Pattern;


public class EmailValidation {
    public static boolean isEmail(String s) {
        final Pattern EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return EMAIL.matcher(s).matches();
    }
}
