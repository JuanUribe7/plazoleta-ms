package com.example.plazoleta.ms_plazoleta.domain.model;

public class Dish {


        private Long id;
        private String name;
        private Integer price;
        private String description;
        private String imageUrl;
        private String category;
        private Long restaurantId;
        private Long ownerId;
        private Boolean active;

    public Dish() {

    }

    public Dish(Long id, String name, Integer price, String description, String imageUrl, String category,
            Long restaurantId, Long ownerId  , Boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.restaurantId = restaurantId;
        this.ownerId = ownerId;
        this.active = active;
    }

        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
