package com.example.plazoleta.ms_plazoleta.domain.usecases.assign;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.assign.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import jakarta.persistence.EntityNotFoundException;

public class AssignEmployeeUseCase implements AssignEmployeeServicePort {

    private final RestaurantPersistencePort restaurantPort;

    public AssignEmployeeUseCase(RestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPort = restaurantPersistencePort;
    }

    @Override
    public void execute(Long restaurantId, Long employeeId, Long ownerId) {
        Restaurant restaurant = restaurantPort.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));

        restaurant.assignEmployee(employeeId, ownerId, restaurantPort);
    }

}
