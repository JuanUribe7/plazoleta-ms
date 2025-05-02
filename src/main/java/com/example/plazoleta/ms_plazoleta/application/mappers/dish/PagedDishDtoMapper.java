package com.example.plazoleta.ms_plazoleta.application.mappers.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.PagedDishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;

import java.util.List;

public class PagedDishDtoMapper {

    private PagedDishDtoMapper() {}

    public static DishSimpleResponseDto toResponseDto(Dish dish) {
        return new DishSimpleResponseDto(
                dish.getId(),
                dish.getName(),
                dish.getPrice(),
                dish.getDescription(),
                dish.getImageUrl(),
                dish.getCategory()
        );
    }

    public static PagedDishResponseDto toDto(PagedResult<Dish> page) {
        List<DishSimpleResponseDto> content = page.getContent()
                .stream()
                .map(PagedDishDtoMapper::toResponseDto)
                .toList();

        return new PagedDishResponseDto(
                content,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getPageSize(),
                page.getCurrentPage(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}