package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DishDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)

    Dish toModel(DishRequestDto dto);
    Dish updToModel(UpdateDishRequestDto dto);

    DishResponseDto toResponseDto(Dish model);

    UpdateDishResponseDto updToResponseDto(Dish model);
}