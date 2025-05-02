package com.example.plazoleta.ms_plazoleta.application.dto.response;

public class DishOrderResponseDto {
    private Long dishId;
    private Integer quantity;

    public DishOrderResponseDto(Long dishId, Integer quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Long getDishId() { return dishId; }
    public Integer getQuantity() { return quantity; }
}