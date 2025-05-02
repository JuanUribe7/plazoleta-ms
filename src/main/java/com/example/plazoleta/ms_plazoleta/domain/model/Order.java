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
