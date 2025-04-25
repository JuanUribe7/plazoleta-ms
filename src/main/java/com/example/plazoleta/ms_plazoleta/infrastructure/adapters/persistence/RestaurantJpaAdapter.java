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
        if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()) {
            throw new IllegalArgumentException("El NIT del restaurante ya está registrado.");
        }
        if (restaurantRepository.findByPhone(restaurant.getPhone()).isPresent()) {
            throw new IllegalArgumentException("El teléfono del restaurante ya está registrado.");
        }
        return mapper.toModel(restaurantRepository.save(mapper.toEntity(restaurant)));
    }

    @Override
    public Optional<Restaurant> findByNit(String nit) {
        return Optional.empty();
    }


    @Override
    public Optional<Restaurant> findByUrlLogo(String logo) {
        return restaurantRepository
                .findByUrlLogo(logo)
                .map(mapper::toModel);
    }
}
