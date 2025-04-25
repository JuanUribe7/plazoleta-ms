package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantPersistencePort
{
    Restaurant saveRestaurant(Restaurant user);
    Optional<Restaurant> findByNit(String nit);
    Optional<Restaurant> findByUrlLogo(String logo);
}
