package com.example.plazoleta.ms_plazoleta.infrastructure.repositories;

import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<DishEntity, Long> {


    Optional<DishEntity> findByName(String name);
    Optional<DishEntity> findById(Long id);
}
