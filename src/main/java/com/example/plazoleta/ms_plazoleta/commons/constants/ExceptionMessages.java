package com.example.plazoleta.ms_plazoleta.commons.constants;

public class ExceptionMessages {

    private ExceptionMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String RESTAURANT_NOT_FOUND = "The restaurant does not exist.";
    public static final String NIT_ALREADY_EXISTS = "A restaurant with this NIT already exists.";
    public static final String DISH_ALREADY_EXISTS = "A dish with this name already exists in the restaurant.";
    public static final String DISH_NOT_FOUND = "The dish was not found.";
    public static final String EMPLOYEE_ALREADY_ASSIGNED = "The employee is already assigned to this restaurant.";
    public static final String USER_NOT_FOUND = "The user was not found.";
    public static final String USER_NOT_OWNER = "The user does not have the OWNER role.";
    public static final String NOT_OWNER_OF_RESTAURANT = "You do not have permission over this restaurant.";
    public static final String UNAUTHORIZED_ACCESS = "Unauthorized access.";
    public static final String DISH_WRONG_RESTAURANT = "The dish does not belong to the restaurant.";
    public static final String UTILITY_CLASS_INSTANTIATION = "Utility class cannot be instantiated";
    public static final String RESTAURANT_NAME_ALREADY_EXISTS = "A restaurant with this name already exists.";
    public static final String LOGO_ALREADY_EXISTS = "A restaurant with this logo already exists.";

}
