package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderDish;

import java.util.List;

public class OrderDtoMapper {

    private OrderDtoMapper() {}

    public static Order toModel(Long clientId, CreateOrderRequestDto dto) {
        List<OrderDish> dishes = dto.getDishes()
                .stream()
                .map(d -> new OrderDish(d.getDishId(), d.getQuantity()))
                .toList();

        return Order.create(clientId, dto.getRestaurantId(), dishes);
    }
}