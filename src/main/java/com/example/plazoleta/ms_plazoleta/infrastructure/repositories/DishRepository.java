package com.example.plazoleta.ms_plazoleta.infrastructure.repositories;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<DishEntity, Long> {


    Optional<DishEntity> findByName(String name);
    Optional<DishEntity> findById(Long id);
    Optional<DishEntity> findByNameAndRestaurantId(String name, Long restaurantId);

    Page<DishEntity> findByRestaurantIdAndCategoryOrderByNameAsc(Long restaurantId, CategoryType categoryType, Pageable pageable);

    Page<DishEntity> findByRestaurantIdOrderByNameAsc(Long restaurantId, Pageable pageable);
}
