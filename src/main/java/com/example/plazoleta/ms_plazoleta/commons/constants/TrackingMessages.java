package com.example.plazoleta.ms_plazoleta.commons.constants;

public class TrackingMessages {

    private TrackingMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String ORDER_READY = "The order is ready to be picked up by the client.";
    public static final String ORDER_IN_PREPARATION = "The order is now being prepared by the employee.";
    public static final String ORDER_CANCELLED = "The client has cancelled the order.";
    public static final String ORDER_DELIVERED = "The order has been delivered successfully.";
}
