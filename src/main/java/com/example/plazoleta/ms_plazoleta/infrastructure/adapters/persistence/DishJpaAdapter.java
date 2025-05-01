package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DishJpaAdapter implements DishPersistencePort {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;


    @Override
    public Dish saveDish(Dish dish) {
        RestaurantEntity restaurant = restaurantRepository.findById(dish.getRestaurantId())
                .orElseThrow(EntityNotFoundException::new);

        DishEntity entity = DishEntityMapper.toEntity(dish, restaurant);
        DishEntity saved = dishRepository.save(entity);

        return DishEntityMapper.toModel(saved);
    }

    @Override
    public Optional<Dish> findByNameAndRestaurantId(String name, Long restaurantId) {
        return dishRepository.findByNameAndRestaurantId(name, restaurantId).map(DishEntityMapper::toModel);
    }



    @Override
    public Dish updateDish(Dish dish) {
        RestaurantEntity restaurant = restaurantRepository.findById(dish.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));

        DishEntity entity = DishEntityMapper.toEntity(dish, restaurant);
        DishEntity saved = dishRepository.save(entity);

        return DishEntityMapper.toModel(saved);
    }


    @Override
    public Optional<Dish> findByName(String name) {
        return dishRepository
                .findByName(name)
                .map(DishEntityMapper::toModel);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id).map(DishEntityMapper::toModel);
    }


}


