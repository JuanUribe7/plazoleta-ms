package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderQueryPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.OrderEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.PagedResultMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderJpaAdapter implements OrderPersistencePort, OrderQueryPort {

    private final OrderRepository repository;

    public OrderJpaAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = OrderEntityMapper.toEntity(order);
        return OrderEntityMapper.toModel(repository.save(entity));
    }

    @Override
    public PagedResult<Order> findByRestaurantAndStatus(Long restaurantId, Optional<OrderStatus> status, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize());
        Page<OrderEntity> page = repository.findByRestaurantIdAndStatus(restaurantId, status, pageable);
        return PagedResultMapper.map(page, OrderEntityMapper::toModel);
    }
}
