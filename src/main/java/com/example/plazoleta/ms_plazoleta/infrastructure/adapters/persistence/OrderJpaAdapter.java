package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.OrderEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJpaAdapter implements OrderPersistencePort {

    private final OrderRepository repository;

    public OrderJpaAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = OrderEntityMapper.toEntity(order);
        return OrderEntityMapper.toModel(repository.save(entity));
    }
}
