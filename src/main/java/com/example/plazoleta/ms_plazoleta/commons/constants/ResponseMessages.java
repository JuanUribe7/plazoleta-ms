package com.example.plazoleta.ms_plazoleta.commons.constants;

public class ResponseMessages {

    public static final String ORDER_READY = "The order is ready to be picked up by the client.";
    public static final String ORDER_CANCELLED = "The client has cancelled the order.";
    public static final String ORDER_DELIVERED = "The order has been delivered successfully.";
    public static final String DISH_CREATED = "DISH CREATED";
    public static final String DISH_STATUS_UPDATED = "DISH STATUS UPDATED";
    public static final String DISH_UPDATED = "DISH UPDATED";


    public static final String ORDER_ASSIGNED = "The order is now being prepared.";
    public static final String ORDER_CREATED = "ORDER CREATED";
    public static final String RESTAURANT_CREATED = "RESTAURANT CREATED";
    public static final String RESTAURANT_EXISTS = "RESTAURANT EXISTS";
    public static final String EMPLOYEE_ASSIGNED = "EMPLOYEE ASSIGNED";

    private ResponseMessages() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }
}
