package com.example.plazoleta.ms_plazoleta.infrastructure.repositories;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<DishEntity, Long> {


    Optional<DishEntity> findByName(String name);
    Optional<DishEntity> findById(Long id);
    boolean existsByName(String name);
    boolean existsByUrlImage(String logo);
    Optional<DishEntity> findByNameAndRestaurantId(String name, Long restaurantId);
    Page<DishEntity> findByRestaurantIdAndCategoryAndActiveTrue(Long restaurantId, CategoryType category, Pageable pageable);
    Page<DishEntity> findByRestaurantIdAndActiveTrue(Long restaurantId, Pageable pageable);
    long countByIdInAndRestaurantId(List<Long> ids, Long restaurantId);
}

