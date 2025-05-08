package com.example.plazoleta.ms_plazoleta.commons.constants;

public class ErrorFieldsMessages {

    private ErrorFieldsMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String DISH_CATEGORY_REQUIRED = "Category is required";
    public static final String DISH_CATEGORY_INVALID = "Invalid category. Valid options: STARTER, DRINK, MAIN, DESSERT";
    public static final String DISH_PRICE_INVALID = "Invalid price. Price must be greater than 0";
    public static final String FIELD_REQUIRED = "%s cannot be empty";
    public static final String FIELD_INVALID_CHARS = "%s can only contain %s";
    public static final String FIELD_LENGTH_RANGE = "%s must be between %d and %d characters";
    public static final String FIELD_MIN_LENGTH = "%s must be at least %d characters";
;


    public static final String DISH_INVALID_PRICE =
            "Price must be a positive number with up to 2 decimal places";
    public static final String DESCRIPTION_INVALID_CHARS =
            "Description can only contain letters, numbers, spaces, periods and commas";
    public static final String ADDRESS_ONLY_NUMBERS = "Address cannot contain only numbers";
    public static final String NAME_DOUBLE_SPACES = "Name cannot have double spaces";
    public static final String NAME_LEADING_TRAILING_SPACES = "Name cannot start or end with spaces";
    public static final String LOGO_LOCAL_URL = "Logo URL cannot be local";
    public static final String LOGO_INVALID_FORMAT = "Logo URL must be valid and start with http or https";
    public static final String LOGO_INVALID_EXTENSION = "Logo URL must end with .png, .jpg, .jpeg or .svg";
    public static final String PHONE_PLUS_POSITION = "'+' can only be at the beginning and once";
    public static final String PHONE_DOUBLE_ZERO = "Number cannot start with '00'";
    public static final String PHONE_INVALID_FORMAT =
            "Number must have between 10 and 13 digits, with optional '+' at the beginning";
    public static final String NIT_INVALID_FORMAT = "NIT must contain only numbers and have between 9 and 12 digits";
    public static final String NIT_ALREADY_EXISTS = "NIT is already registered";
    public static final String INVALID_CATEGORY = "Invalid category. Valid options: STARTER, DRINK, MAIN_COURSE, DESSERT";
    public static final String STATUS_ORDER_REQUIRED= "Status is required. Valid options: PENDING,  IN_PREPARATION, READY, DELIVERED, CANCELED";
}

