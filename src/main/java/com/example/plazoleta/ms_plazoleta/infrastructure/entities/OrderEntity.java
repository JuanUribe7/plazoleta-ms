package com.example.plazoleta.ms_plazoleta.infrastructure.entities;

import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "assigned_employee_id")
    private Long assignedEmployeeId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // dueño de la relación
    private List<OrderDishEntity> dishOrders = new ArrayList<>();

    public OrderEntity() {}


}
