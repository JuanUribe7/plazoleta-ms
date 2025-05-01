package com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant;

public interface AssignEmployeeServicePort {
    void execute(Long restaurantId, Long employeeId, Long ownerId);
}
