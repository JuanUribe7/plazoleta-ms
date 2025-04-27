package com.example.plazoleta.ms_plazoleta.domain.ports.in;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantServicePort {
    Restaurant createRestaurant(Restaurant user);
    Optional<Restaurant> findById(Long id);
}
