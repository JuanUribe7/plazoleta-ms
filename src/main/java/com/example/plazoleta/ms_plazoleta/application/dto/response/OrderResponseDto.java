package com.example.plazoleta.ms_plazoleta.application.dto.response;


import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private Long clientId;
    private Long restaurantId;
    private OrderStatus status;
    private LocalDateTime date;
    private Long assignedEmployeeId;
    private List<DishOrderResponseDto> dishes;

    public OrderResponseDto(Long id, Long clientId, Long restaurantId, OrderStatus status,
                            LocalDateTime date, Long assignedEmployeeId, List<DishOrderResponseDto> dishes) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.status = status;
        this.date = date;
        this.assignedEmployeeId = assignedEmployeeId;
        this.dishes = dishes;
    }

    public Long getId() { return id; }
    public Long getClientId() { return clientId; }
    public Long getRestaurantId() { return restaurantId; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getDate() { return date; }
    public Long getAssignedEmployeeId() { return assignedEmployeeId; }
    public List<DishOrderResponseDto> getDishes() { return dishes; }
}