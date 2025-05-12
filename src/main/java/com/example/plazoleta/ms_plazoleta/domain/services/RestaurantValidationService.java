package com.example.plazoleta.ms_plazoleta.domain.services;


import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.RestaurantValidationFields;

public class RestaurantValidationService implements RestaurantValidationFields {
    private final ValidationFieldsService validationFieldsService;

    public RestaurantValidationService(ValidationFieldsService validationFieldsService) {
        this.validationFieldsService = validationFieldsService;
    }

    @Override
    public void validateRestaurant(Restaurant restaurant) {
        validationFieldsService.validateName(restaurant.getName());
        validationFieldsService.validateNit(restaurant.getNit());
        validationFieldsService.validateAddress(restaurant.getAddress());
        validationFieldsService.validatePhone(restaurant.getPhone());
        validationFieldsService.validateLogo(restaurant.getUrlLogo());

    }
}
