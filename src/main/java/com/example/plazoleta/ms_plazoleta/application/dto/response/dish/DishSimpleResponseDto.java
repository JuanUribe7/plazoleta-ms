package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;

public class DishSimpleResponseDto {
    private String name;
    private Integer price;
    private String description;

    public DishSimpleResponseDto() {}

    public DishSimpleResponseDto(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // Getters opcionales si no usas Lombok
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public String getDescription() { return description; }
}

