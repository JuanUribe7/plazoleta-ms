package com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish updateDish(Dish dish);
    long countByIdInAndRestaurantId(List<Long> dishIds, Long restaurantId);
    Optional<Dish> findByNameAndRestaurantId(String name, Long restaurantId);
    Optional<Dish> findByName(String name);
    Optional<Dish> findById(Long id);

    Page<Dish> findByRestaurantIdAndCategoryAndActiveTrue(Long restaurantId, String category, Pageable pageable);

    Page<Dish> findByRestaurantIdAndActiveTrue(Long restaurantId, Pageable pageable);

    boolean existsByName(String name);

    boolean existsByUrlImage(String imageUrl);
}
