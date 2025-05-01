package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import java.util.Optional;

public interface RestaurantPersistencePort
{
    Restaurant saveRestaurant(Restaurant user);
    Optional<Restaurant> findByNit(String nit);
    Optional<Restaurant> findByUrlLogo(String logo);
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByName(String name);
    PagedResult<Restaurant> findAllOrderedByName(Pagination pagination);
}
