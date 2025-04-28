package com.example.plazoleta.ms_plazoleta.application.dto.request;


import jakarta.validation.constraints.NotBlank;

public class DishRequestDto {

    @NotBlank
    private String name;
    private Integer price;

    private String description;
    private String imageUrl;
    private String category;
    private Long ownerId;
    private Long restaurantId;

    public DishRequestDto() {
    }

    public DishRequestDto(String name, Integer price, String description, String imageUrl, String category, Long restaurantId, Long ownerId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.restaurantId = restaurantId;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}