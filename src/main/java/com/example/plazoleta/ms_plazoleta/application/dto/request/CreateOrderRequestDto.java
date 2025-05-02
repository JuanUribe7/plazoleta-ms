package com.example.plazoleta.ms_plazoleta.application.dto.request;

import java.util.List;

public class CreateOrderRequestDto {
    private Long restaurantId;
    private List<DishOrderRequestDto> dishes;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public List<DishOrderRequestDto> getDishes() {
        return dishes;
    }
}