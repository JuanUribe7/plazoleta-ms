package com.example.plazoleta.ms_plazoleta.domain.utils.validation.existenceandrelation;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;

public class EmployeeAuthorizationValidator {

    private EmployeeAuthorizationValidator() {}

    public static void validateEmployeeBelongsToRestaurant(
            Long restaurantId,
            Long employeeId,
            RestaurantPersistencePort port
    ) {
        Restaurant restaurant = ExistenceValidator.getIfPresent(
                port.findById(restaurantId),
                ExceptionMessages.RESTAURANT_NOT_FOUND
        );

        RelationValidator.validateCondition(
                restaurant.getEmployeeIds().contains(employeeId),
                ExceptionMessages.EMPLOYEE_NOT_ASSIGNED_TO_RESTAURANT
        );

    }
}
