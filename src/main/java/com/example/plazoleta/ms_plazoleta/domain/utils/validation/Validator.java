package com.example.plazoleta.ms_plazoleta.domain.utils.validation;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;


public class Validator {

    private Validator() {
        throw new UnsupportedOperationException("Clase utilitaria, no debe instanciarse.");
    }


    public static void validate(Restaurant restaurant , IRestaurantPersistencePort restaurantPersistencePort) {
        NitValidator.validate(restaurant.getNit(), restaurantPersistencePort);
        OwnerValidator.validate(restaurant.getOwnerId());
        AddressValidator.validate(restaurant.getAddress());
        NameValidator.validate(restaurant.getName());
        PhoneValidator.validate(restaurant.getPhone());
        LogoValidator.validate(restaurant.getUrlLogo());

    }
}
