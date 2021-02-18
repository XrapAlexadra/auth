package edu.epam.auth.validator;

import edu.epam.auth.model.User;

import java.util.HashMap;
import java.util.Map;

import static edu.epam.auth.constant.MessageConstant.*;

public class UserValidator {

    private static final String LOGIN_MATCHER = "^[\\w]{1,20}$";
    private static final String PASSWORD_MATCHER = "^[\\w]{1,20}$";
    private static final String EMAIL_MATCHER = "^[-\\w]+@[\\w]+\\.[A-z]{2,4}$";

    private UserValidator() {
    }

    public static Map<String, String> isValid(User user, String password, String repeatPassword) {
        String login = user.getLogin();
        String email = user.getEmail();
        Map<String, String> invalidFields = new HashMap<>();
        if (!isValidLogin(login)) {
            invalidFields.put(login, INVALID_LOGIN);
        }
        if (password.equals(repeatPassword)) {
            if (!isValidPassword(password)) {
                invalidFields.put(password, INVALID_PASSWORD);
            }
        }
        else {
            invalidFields.put(password, WRONG_REPEAT_PASSWORD);
        }
        if (!isValidEmail(email)) {
            invalidFields.put(email, INVALID_EMAIL);
        }
        return invalidFields;
    }


    private static boolean isValidEmail(String email) {
        return (email != null && email.matches(EMAIL_MATCHER));
    }

    public static boolean isValidLogin(String login) {
        return (login != null && login.matches(LOGIN_MATCHER));
    }

    public static boolean isValidPassword(String password) {
        return (password != null && password.matches(PASSWORD_MATCHER));
    }

}
