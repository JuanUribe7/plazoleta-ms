package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;


import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishEntityMapper {
    Dish toModel(DishEntity entity);
    DishEntity toEntity(Dish model);
}