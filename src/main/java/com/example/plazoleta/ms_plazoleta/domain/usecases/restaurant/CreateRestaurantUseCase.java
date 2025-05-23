package com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant;

import com.example.plazoleta.ms_plazoleta.commons.constants.FieldNames;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.RestaurantValidationFields;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.services.RestaurantValidationService;
import com.example.plazoleta.ms_plazoleta.domain.services.ValidationFieldsService;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.UniquenessValidator;

public class CreateRestaurantUseCase implements CreateRestaurantServicePort {

    private final RestaurantPersistencePort restaurantPort;
    private final UserValidationPort userValidationPort;
    private final RestaurantValidationFields restaurantValidationFields;

    public CreateRestaurantUseCase(RestaurantPersistencePort restaurantPort,
                                   UserValidationPort userValidationPort,
                                   RestaurantValidationFields restaurantValidationFields){
        this.restaurantPort = restaurantPort;
        this.userValidationPort = userValidationPort;
        this.restaurantValidationFields = restaurantValidationFields;
    }

    @Override
    public void execute(Restaurant restaurant) {
        restaurantValidationFields.validateRestaurant(restaurant);
        UniquenessValidator.validate(() -> restaurantPort.existsByNit(restaurant.getNit()), FieldNames.NIT, restaurant.getNit());
        UniquenessValidator.validate(() -> restaurantPort.existsByName(restaurant.getName()), FieldNames.NAME, restaurant.getName());
        UniquenessValidator.validate(() -> restaurantPort.existsByNit(restaurant.getUrlLogo()), FieldNames.LOGO, restaurant.getUrlLogo());
        userValidationPort.validateOwnerExists(restaurant.getOwnerId());
        restaurantPort.saveRestaurant(restaurant);
    }


}
