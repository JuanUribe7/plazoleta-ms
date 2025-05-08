package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;

public class RestaurantCreateValidator {

    private RestaurantCreateValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }

    public static void validateFields(Restaurant restaurant) {
        NameValidator.validate(restaurant.getName());
        PhoneValidator.validate(restaurant.getPhone());
        AddressValidator.validate(restaurant.getAddress());
        LogoValidator.validate(restaurant.getUrlLogo());
    }

    public static void validateUniqueness(Restaurant restaurant, RestaurantPersistencePort port) {
        ExistenceValidator.validateDoesNotExist(port.findByNit(restaurant.getNit()), ExceptionMessages.NIT_ALREADY_EXISTS);
        ExistenceValidator.validateDoesNotExist(port.findByName(restaurant.getName()), ExceptionMessages.RESTAURANT_NAME_ALREADY_EXISTS);
        ExistenceValidator.validateDoesNotExist(port.findByUrlLogo(restaurant.getUrlLogo()), ExceptionMessages.LOGO_ALREADY_EXISTS);

    }

    public static void validateOwnerExists(Long ownerId, UserValidationPort userPort) {
        userPort.validateOwnerExists(ownerId);
    }

    public static void validateAssignment(Restaurant restaurant, Long employeeId, Long ownerId) {
        RelationValidator.validateCondition(
                restaurant.getOwnerId().equals(ownerId),
                ExceptionMessages.NOT_OWNER_OF_RESTAURANT
        );

        RelationValidator.validateCondition(
                !restaurant.getEmployeeIds().contains(employeeId),
                ExceptionMessages.EMPLOYEE_ALREADY_ASSIGNED
        );
    }
}