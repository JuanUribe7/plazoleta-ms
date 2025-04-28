package com.example.plazoleta.ms_plazoleta.application.dto.request;

public class UpdateDishRequestDto {

    private Long dishId;
    private Integer price;
    private String description;
    private Long ownerId;
    private Long restaurantId;

    public UpdateDishRequestDto() {
    }

    public UpdateDishRequestDto(Long dishId, Integer price, String description, Long ownerId, Long restaurantId) {
        this.dishId = dishId;
        this.price = price;
        this.description = description;
        this.ownerId = ownerId;
        this.restaurantId = restaurantId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
