package com.example.plazoleta.ms_plazoleta.domain.model;


import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private final Long id;
    private final Long clientId;
    private final Long restaurantId;
    private final List<OrderDish> dishes;
    private final LocalDateTime date;
    private final OrderStatus status;
    private final Long assignedEmployeeId;


    public Order(
            Long id,
            Long clientId,
            Long restaurantId,
            List<OrderDish> dishes,
            LocalDateTime date,
            OrderStatus status,
            Long assignedEmployeeId

    ) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.date = date;
        this.status = status;
        this.assignedEmployeeId = assignedEmployeeId;
    }

    public Order markAsReady() {
        if (!OrderStatus.PREPARING.equals(this.status)) {
            throw new IllegalStateException(ExceptionMessages.ONLY_IN_PREPARATION_CAN_BE_READY);
        }
        return new Order(id, clientId, restaurantId, dishes, date, OrderStatus.READY, assignedEmployeeId);
    }
    public Order markAsDelivered() {
        if (!OrderStatus.READY.equals(this.status)) {
            throw new IllegalStateException(ExceptionMessages.ONLY_READY_CAN_BE_DELIVERED);
        }
        return new Order(id, clientId, restaurantId, dishes, date, OrderStatus.DELIVERED, assignedEmployeeId);
    }

    public Order markAsCancelled() {
        if (!OrderStatus.PENDING.equals(this.status)) {
            throw new IllegalStateException(ExceptionMessages.ONLY_PENDING_CAN_BE_CANCELLED);
        }
        return new Order(id, clientId, restaurantId, dishes, date, OrderStatus.CANCELLED, assignedEmployeeId);
    }


    public Order assignEmployee(Long employeeId, Long orderId) {
        if (!OrderStatus.PENDING.equals(this.status)) {
            throw new IllegalStateException(ExceptionMessages.ONLY_PENDING_CAN_BE_ASSIGNED);
        }


        return new Order(
                orderId,
                this.clientId,
                this.restaurantId,
                this.dishes,
                this.date,
                OrderStatus.PREPARING,
                employeeId
        );
    }

    public static Order create(Long clientId, Long restaurantId, List<OrderDish> dishes) {
        return new Order(
                null,
                clientId,
                restaurantId,
                dishes,
                LocalDateTime.now(),
                OrderStatus.PENDING,
                null
        );
    }

    public Long getId() { return id; }
    public Long getClientId() { return clientId; }
    public Long getRestaurantId() { return restaurantId; }
    public List<OrderDish> getDishes() { return dishes; }
    public LocalDateTime getDate() { return date; }
    public OrderStatus getStatus() { return status; }
    public Long getAssignedEmployeeId() { return assignedEmployeeId; }


}
