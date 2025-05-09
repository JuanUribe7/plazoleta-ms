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
    public static final String CLIENT_ALREADY_HAVE_ORDER = "The client already has an order in preparation.";
    public static final String ONLY_IN_PREPARATION_CAN_BE_READY = "Only dishes in preparation can be marked as ready.";
    public static final String ORDER_NOT_FOUND = "The order was not found.";
    public static final String ORDER_WRONG_RESTAURANT = "The order does not belong to the restaurant.";
    public static final String EMPLOYEE_NOT_ASSIGNED_TO_RESTAURANT = "The employee is not assigned to this restaurant.";



    public static final String ONLY_PENDING_CAN_BE_ASSIGNED = "Only orders in PENDING status can be assigned.";
    public static final String ONLY_READY_CAN_BE_DELIVERED = "Only READY orders can be marked as DELIVERED.";
    public static final String ONLY_PENDING_CAN_BE_CANCELLED = "Only PENDING orders can be marked as CANCELLED.";
    public static final String INVALID_ADDRESS = "The address contains invalid characters.";



}
