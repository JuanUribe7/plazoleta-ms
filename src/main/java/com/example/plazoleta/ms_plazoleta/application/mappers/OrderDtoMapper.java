package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderDish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;

import java.util.List;

public class OrderDtoMapper {

    private OrderDtoMapper() {}

    public static Order toModel(Long restaurantId,Long clientId, CreateOrderRequestDto dto) {
        List<OrderDish> dishes = dto.getDishes()
                .stream()
                .map(d -> new OrderDish(d.getDishId(), d.getQuantity()))
                .toList();

        return Order.create(clientId, restaurantId, dishes);
    }

    public static OrderResponseDto toDto(Order order) {
        List<DishOrderResponseDto> dishDtos = order.getDishes().stream()
                .map(d -> new DishOrderResponseDto(d.getDishId(), d.getQuantity()))
                .toList();

        return new OrderResponseDto(
                order.getId(),
                order.getClientId(),
                order.getRestaurantId(),
                order.getStatus(),
                order.getDate(),
                order.getAssignedEmployeeId(),
                dishDtos
        );

}

    public static PagedOrderResponseDto toDto(PagedResult<Order> page) {
        List<OrderResponseDto> content = page.getContent().stream()
                .map(OrderDtoMapper::toDto)
                .toList();

        return new PagedOrderResponseDto(
                content,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getPageSize(),
                page.getCurrentPage(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}