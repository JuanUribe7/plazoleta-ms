package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;


public class RestaurantValidator {

    private RestaurantValidator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }


    public static void validate(Restaurant restaurant) {
        NitValidator.validate(restaurant.getNit());
        OwnerValidator.validate(restaurant.getOwnerId());
        AddressValidator.validate(restaurant.getAddress());
        NameValidator.validate(restaurant.getName());
        PhoneValidator.validate(restaurant.getPhone());
        LogoValidator.validate(restaurant.getUrlLogo());

    }
}
