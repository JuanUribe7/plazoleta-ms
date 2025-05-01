package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;


import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;

public class DishEntityMapper {

    private DishEntityMapper() {}

    public static DishEntity toEntity(Dish dish, RestaurantEntity restaurant) {
        DishEntity entity = new DishEntity();
        entity.setId(dish.getId());
        entity.setName(dish.getName());
        entity.setPrice(dish.getPrice().doubleValue());
        entity.setDescription(dish.getDescription());
        entity.setUrlImage(dish.getImageUrl());
        entity.setAvailable(dish.isActive());
        entity.setCategory(dish.getCategory());
        entity.setRestaurant(restaurant);
        return entity;
    }


    public static DishEntity toEntity(Dish model) {
        throw new UnsupportedOperationException("No usar este método sin categoría ni restaurante resueltos");
    }

    public static Dish toModel(DishEntity entity) {
        return new Dish(
                entity.getId(),
                entity.getName(),
                entity.getPrice().intValue(),
                entity.getDescription(),
                entity.getUrlImage(),
                entity.isAvailable(),
                entity.getRestaurant().getId(),
                entity.getCategory()
        );
    }
}