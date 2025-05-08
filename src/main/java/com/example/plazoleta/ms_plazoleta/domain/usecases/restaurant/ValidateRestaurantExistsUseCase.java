package com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.ValidateRestaurantExistsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;

import java.util.List;

public class ValidateRestaurantExistsUseCase implements ValidateRestaurantExistsServicePort {

    private final RestaurantPersistencePort restaurantPersistencePort;

    public ValidateRestaurantExistsUseCase(RestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void validate(Long restaurantId) {
        ExistenceValidator.getIfPresent(
            restaurantPersistencePort.findById(restaurantId),
            ExceptionMessages.RESTAURANT_NOT_FOUND
        );
    }

    @Override
    public List<Long> getEmployeeIdsByOwner(Long ownerId) {
        return restaurantPersistencePort.findAllByOwnerId(ownerId).stream()
                .flatMap(r -> r.getEmployeeIds().stream())
                .distinct()
                .toList();
    }


}
