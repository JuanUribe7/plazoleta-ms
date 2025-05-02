package com.example.plazoleta.ms_plazoleta.infrastructure.entities;


import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clientId;
    private Long restaurantId;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Long assignedEmployeeId;

    @Column(length = 6)
    private String pin;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDishEntity> dishOrders;

    public OrderEntity() {}

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Long getAssignedEmployeeId() { return assignedEmployeeId; }
    public void setAssignedEmployeeId(Long assignedEmployeeId) { this.assignedEmployeeId = assignedEmployeeId; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public List<OrderDishEntity> getDishOrders() { return dishOrders; }
    public void setDishOrders(List<OrderDishEntity> dishOrders) { this.dishOrders = dishOrders; }
}