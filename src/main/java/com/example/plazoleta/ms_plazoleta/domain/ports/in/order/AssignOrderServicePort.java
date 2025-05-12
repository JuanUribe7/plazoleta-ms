package com.example.plazoleta.ms_plazoleta.domain.ports.in.order;


public interface AssignOrderServicePort {
    void assignToOrder(Long restaurantId, Long orderId, Long employeeId);
}