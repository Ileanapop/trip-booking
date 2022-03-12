package service.validators.UserData;

import java.util.regex.Pattern;

public class EmailValidator{

    private static final String EMAIL_PATTERN = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
}
