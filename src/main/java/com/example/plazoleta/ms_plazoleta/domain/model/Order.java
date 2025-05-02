package com.example.plazoleta.ms_plazoleta.domain.model;


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
    private final String pin;

    public Order(
            Long id,
            Long clientId,
            Long restaurantId,
            List<OrderDish> dishes,
            LocalDateTime date,
            OrderStatus status,
            Long assignedEmployeeId,
            String pin
    ) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.date = date;
        this.status = status;
        this.assignedEmployeeId = assignedEmployeeId;
        this.pin = pin;
    }

    public Order markAsReady(String generatedPin) {
        if (!OrderStatus.IN_PREPARATION.equals(this.status)) {
            throw new IllegalStateException("Only IN_PREPARATION orders can be marked as READY.");
        }
        return new Order(id, clientId, restaurantId, dishes, date, OrderStatus.READY, assignedEmployeeId, generatedPin);
    }


    public Order assignEmployee(Long employeeId, Long orderId) {
        if (!OrderStatus.PENDING.equals(this.status)) {
            throw new IllegalStateException("Only orders in PENDING status can be assigned.");
        }

        return new Order(
                orderId,
                this.clientId,
                this.restaurantId,
                this.dishes,
                this.date,
                OrderStatus.IN_PREPARATION,
                employeeId,
                pin
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
                null,
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
    public String getPin() { return pin; }
}
