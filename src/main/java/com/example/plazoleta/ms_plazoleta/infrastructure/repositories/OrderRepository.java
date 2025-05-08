package com.example.plazoleta.ms_plazoleta.infrastructure.repositories;

import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findByStatusAndRestaurantId(OrderStatus status, Long restaurantId, Pageable pageable);
    Optional<OrderEntity> findFirstByClientIdAndStatusIn(Long clientId, java.util.List<OrderStatus> statuses);
}