package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderDish;

import java.util.List;

public class OrderMapper {

    private OrderMapper() {}

    public static Order toModel(CreateOrderRequestDto dto,Long restaurantId, Long clientId ) {
        List<OrderDish> dishes = dto.dishes().stream()
            .map(d -> new OrderDish(d.dishId(), d.quantity()))
            .toList();

        return Order.create(clientId, restaurantId, dishes);
    }

    public static OrderResponseDto toResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getClientId(),
                order.getRestaurantId(),
                order.getStatus(),
                order.getAssignedEmployeeId(),
                order.getDishes().stream()
                        .map(d -> new DishOrderResponseDto(d.getDishId(), d.getQuantity()))
                        .toList()
        );
    }
}
