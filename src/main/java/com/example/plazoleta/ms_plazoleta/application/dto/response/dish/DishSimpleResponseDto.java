package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;


import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;

public class DishSimpleResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String imageUrl;
    private CategoryType category;

    public DishSimpleResponseDto(Long id, String name, Integer price, String description, String imageUrl, CategoryType category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public CategoryType getCategory() { return category; }
}