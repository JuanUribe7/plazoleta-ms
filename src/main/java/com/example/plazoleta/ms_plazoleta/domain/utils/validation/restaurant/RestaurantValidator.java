package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;

public class RestaurantValidator {

    private RestaurantValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validateFields(Restaurant restaurant) {
        NameValidator.validate(restaurant.getName());
        PhoneValidator.validate(restaurant.getPhone());
        AddressValidator.validate(restaurant.getAddress());
        LogoValidator.validate(restaurant.getUrlLogo());
    }

    public static void validateUniqueness(Restaurant restaurant, RestaurantPersistencePort port) {
        if (port.findByNit(restaurant.getNit()).isPresent()) {
            throw new IllegalArgumentException(ExceptionMessages.NIT_ALREADY_EXISTS);
        }
        if (port.findByName(restaurant.getName()).isPresent()) {
            throw new IllegalArgumentException(ExceptionMessages.RESTAURANT_NAME_ALREADY_EXISTS);
        }
        if (port.findByUrlLogo(restaurant.getUrlLogo()).isPresent()) {
            throw new IllegalArgumentException(ExceptionMessages.LOGO_ALREADY_EXISTS);
        }

    }

    public static void validateOwnerExists(Long ownerId, UserValidationPort userPort) {
        userPort.validateOwnerExists(ownerId);
    }

    public static void validateAssignment(Restaurant restaurant, Long employeeId, Long ownerId) {
        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new SecurityException(ExceptionMessages.NOT_OWNER_OF_RESTAURANT);
        }

        if (restaurant.getEmployeeIds().contains(employeeId)) {
            throw new IllegalArgumentException(ExceptionMessages.EMPLOYEE_ALREADY_ASSIGNED);
        }
    }
}