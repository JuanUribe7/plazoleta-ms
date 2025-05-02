package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

public class EmployeeAuthorizationValidator {

    private EmployeeAuthorizationValidator() {}

    public static void validateEmployeeBelongsToRestaurant(
            Long restaurantId,
            Long employeeId,
            RestaurantPersistencePort port
    ) {
        Restaurant restaurant = port.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found."));

        boolean belongs = restaurant.getEmployeeIds().contains(employeeId);

        if (!belongs) {
            throw new SecurityException("Employee does not belong to this restaurant.");
        }
    }
}
