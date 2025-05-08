package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.RestaurantEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;

import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements RestaurantPersistencePort {

    private final RestaurantRepository restaurantRepository;


    public RestaurantJpaAdapter(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return RestaurantEntityMapper.toModel(restaurantRepository.save(RestaurantEntityMapper.toEntity(restaurant)));
    }

    @Override
    public Optional<Restaurant> findByNit(String nit) {

        return restaurantRepository
                .findByNit(nit)
                .map(RestaurantEntityMapper::toModel);
    }


    @Override
    public Optional<Restaurant> findByName(String name) {

        return restaurantRepository
                .findByName(name)
                .map(RestaurantEntityMapper::toModel);
    }

    @Override
    public List<Restaurant> findAllByOwnerId(Long ownerId) {
        return restaurantRepository.findAllByOwnerId(ownerId).stream()
                .map(RestaurantEntityMapper::toModel)
                .toList();
    }


    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository
                .findById(id)
                .map(RestaurantEntityMapper::toModel);
    }


    @Override
    public Optional<Restaurant> findByUrlLogo(String logo) {
        return restaurantRepository
                .findByUrlLogo(logo)
                .map(RestaurantEntityMapper::toModel);
    }
}
