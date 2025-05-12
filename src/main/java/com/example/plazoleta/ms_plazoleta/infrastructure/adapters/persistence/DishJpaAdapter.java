package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DishJpaAdapter implements DishPersistencePort {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;


    @Override
    public Dish saveDish(Dish dish) {
        RestaurantEntity restaurant = restaurantRepository.findById(dish.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));
        DishEntity entity = DishEntityMapper.toEntity(dish, restaurant);
        DishEntity saved = dishRepository.save(entity);

        return DishEntityMapper.toModel(saved);
    }

    @Override
    public Optional<Dish> findByNameAndRestaurantId(String name, Long restaurantId) {
        return dishRepository.findByNameAndRestaurantId(name, restaurantId).map(DishEntityMapper::toModel);
    }

    @Override
    public long countByIdInAndRestaurantId(List<Long> dishIds, Long restaurantId) {
        return dishRepository.countByIdInAndRestaurantId(dishIds, restaurantId);
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

    @Override
    public Page<Dish> findByRestaurantIdAndCategoryAndActiveTrue(Long restaurantId, String category, Pageable pageable) {
        return dishRepository.findByRestaurantIdAndCategoryAndActiveTrue(restaurantId, CategoryType.valueOf(category), pageable)
                .map(DishEntityMapper::toModel);
    }

    @Override
    public Page<Dish> findByRestaurantIdAndActiveTrue(Long restaurantId, Pageable pageable) {
        return dishRepository.findByRestaurantIdAndActiveTrue(restaurantId, pageable)
                .map(DishEntityMapper::toModel);
    }

    @Override
    public boolean existsByName(String name) {
        return dishRepository.existsByName(name);
    }
    @Override
    public boolean existsByUrlImage(String imageUrl) {
        return dishRepository.existsByUrlImage(imageUrl);
    }


}


