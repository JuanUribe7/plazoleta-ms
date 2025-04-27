package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.RestaurantEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper mapper;

    public RestaurantJpaAdapter(RestaurantRepository restaurantRepository, @Qualifier("restaurantEntityMapperImpl") RestaurantEntityMapper mapper) {
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }


    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return mapper.toModel(restaurantRepository.save(mapper.toEntity(restaurant)));
    }

    @Override
    public Optional<Restaurant> findByNit(String nit) {

        return restaurantRepository
                .findByNit(nit)
                .map(mapper::toModel);
    }
    @Override
    public Optional<Restaurant> findByName(String name) {

        return restaurantRepository
                .findByName(name)
                .map(mapper::toModel);
    }


    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository
                .findById(id)
                .map(mapper::toModel);
    }


    @Override
    public Optional<Restaurant> findByUrlLogo(String logo) {
        return restaurantRepository
                .findByUrlLogo(logo)
                .map(mapper::toModel);
    }
}
