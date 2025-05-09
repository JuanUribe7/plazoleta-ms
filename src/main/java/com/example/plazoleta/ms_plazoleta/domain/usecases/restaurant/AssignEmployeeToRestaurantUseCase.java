package com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;


public class AssignEmployeeToRestaurantUseCase implements AssignEmployeeServicePort {

    private final RestaurantPersistencePort restaurantPort;

    public AssignEmployeeToRestaurantUseCase(RestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPort = restaurantPersistencePort;
    }

    @Override
    public void execute(Long restaurantId, Long employeeId, Long ownerId) {
        Restaurant restaurant = ExistenceValidator.getIfPresent(
                restaurantPort.findById(restaurantId),
                ExceptionMessages.RESTAURANT_NOT_FOUND
        );
        restaurant.assignEmployee(employeeId, ownerId, restaurantPort);
    }

}
