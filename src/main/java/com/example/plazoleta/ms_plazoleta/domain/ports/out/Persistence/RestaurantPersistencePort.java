package com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RestaurantPersistencePort
{
    Restaurant saveRestaurant(Restaurant user);
    Optional<Restaurant> findByNit(String nit);
    Optional<Restaurant> findByUrlLogo(String logo);
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByName(String name);


    List<Restaurant> findAllByOwnerId(Long ownerId);
}
