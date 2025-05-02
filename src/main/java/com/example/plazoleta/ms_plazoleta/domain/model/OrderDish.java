package com.example.plazoleta.ms_plazoleta.domain.model;

public class OrderDish {

    private final Long dishId;
    private final Integer quantity;

    public OrderDish(Long dishId, Integer quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Long getDishId() {
        return dishId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}