package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public PagedResult<Dish> findByRestaurantWithFilter(Long restaurantId, Optional<CategoryType> category, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        Page<DishEntity> result;

        if (category.isPresent()) {
            result = dishRepository.findByRestaurantIdAndCategoryOrderByNameAsc(restaurantId, category.get(), pageable);
        } else {
            result = dishRepository.findByRestaurantIdOrderByNameAsc(restaurantId, pageable);
        }

        List<Dish> dishes = result.getContent().stream()
                .map(DishEntityMapper::toModel)
                .toList();

        return new PagedResult<>(
                dishes,
                result.getTotalPages(),
                result.getTotalElements(),
                pageable.getPageSize(),
                result.getNumber(),
                result.isFirst(),
                result.isLast(),
                result.hasNext(),
                result.hasPrevious()
        );
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


