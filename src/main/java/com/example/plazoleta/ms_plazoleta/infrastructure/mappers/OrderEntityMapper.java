package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderDish;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderDishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderEntityMapper {

    private OrderEntityMapper() {}

    public static OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setClientId(order.getClientId());
        entity.setRestaurantId(order.getRestaurantId());
        entity.setDate(order.getDate());
        entity.setStatus(order.getStatus());
        entity.setAssignedEmployeeId(order.getAssignedEmployeeId());



        List<OrderDishEntity> dishEntities = order.getDishes().stream()
                .map(dish -> {
                    OrderDishEntity dishEntity = new OrderDishEntity(null, dish.getDishId(), dish.getQuantity());
                    dishEntity.setOrder(entity);
                    return dishEntity;
                })
                .collect(Collectors.toList());

        entity.setDishOrders(dishEntities);

        return entity;
    }

    public static Order toModel(OrderEntity entity) {
        List<OrderDish> dishes = entity.getDishOrders().stream()
                .map(d -> new OrderDish(d.getDishId(), d.getQuantity()))
                .collect(Collectors.toList());

        return new Order(
                entity.getId(),
                entity.getClientId(),
                entity.getRestaurantId(),
                dishes,
                entity.getDate(),
                entity.getStatus(),
                entity.getAssignedEmployeeId()
        );
    }
}