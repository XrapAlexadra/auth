package edu.epam.auth.validator;

public class CategoryValidator {

    private static final String NAME_MATCHER = "^[\\w]{1,20}$";

    public static boolean isValidName(String name){
        return (name != null && name.matches(NAME_MATCHER));
    }
}
