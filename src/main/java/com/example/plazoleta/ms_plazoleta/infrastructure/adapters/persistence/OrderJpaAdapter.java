package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.OrderEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderJpaAdapter implements OrderPersistencePort {

    private final OrderRepository repository;

    public OrderJpaAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderEntityMapper.toEntity(order);
        return OrderEntityMapper.toModel(repository.save(entity));
    }

    @Override
    public boolean existsByClientIdAndStatuses(Long clientId, List<OrderStatus> statuses) {
        return repository.findFirstByClientIdAndStatusIn(clientId, statuses).isPresent();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(OrderEntityMapper::toModel);
    }

}
