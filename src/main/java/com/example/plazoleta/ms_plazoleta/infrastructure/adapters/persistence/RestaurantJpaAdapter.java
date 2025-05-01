package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.RestaurantEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import com.example.plazoleta.ms_plazoleta.infrastructure.utils.PageableFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RestaurantJpaAdapter implements RestaurantPersistencePort {

    private final RestaurantRepository restaurantRepository;


    public RestaurantJpaAdapter(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
  ;
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
    public Page<RestaurantEntity> findAllOrderedByName(Pagination pagination) {
        Pageable pageable = PageableFactory.from(pagination);
        return restaurantRepository.findAll(pageable); // sin mapear aún
    }

    @Override
    public Optional<Restaurant> findByName(String name) {

        return restaurantRepository
                .findByName(name)
                .map(RestaurantEntityMapper::toModel);
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
