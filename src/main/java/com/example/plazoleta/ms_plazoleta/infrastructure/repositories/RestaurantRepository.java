package com.example.plazoleta.ms_plazoleta.infrastructure.repositories;

import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    Optional<RestaurantEntity> findByNit(String nit);
    Optional<RestaurantEntity> findByName(String name);
    Optional<RestaurantEntity> findByUrlLogo(String logo);
    Optional<RestaurantEntity> findById(Long id);

    @Query("SELECT r FROM RestaurantEntity r WHERE :employeeId MEMBER OF r.employeesId")
    Optional<RestaurantEntity> findByEmployeesIdContains(Long employeeId);


}
