package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.pagination;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderQueryPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.OrderEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderQueryAdapter implements OrderQueryPort {

    private final OrderRepository repository;

    public OrderQueryAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaginatedResult<Order> findAllByStatusAndRestaurant(String status, Long restaurantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderEntity> result = repository.findByStatusAndRestaurantId(OrderStatus.valueOf(status.toUpperCase()), restaurantId, pageable);
        List<Order> content = result.getContent().stream().map(OrderEntityMapper::toModel).toList();
        return new PaginatedResult<>(content, page, size, result.getTotalElements(), result.getTotalPages());
    }
}
