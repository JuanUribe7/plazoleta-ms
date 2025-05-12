package com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;


public interface RestaurantValidationFields {
    void validateRestaurant(Restaurant restaurant);
}
