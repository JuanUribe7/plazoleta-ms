package com.example.plazoleta.ms_plazoleta.application.mappers.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.PagedDishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;

import java.util.List;

public class PagedDishDtoMapper {
    public static PagedDishResponseDto toDto(PagedResult<Dish> result) {
        List<DishSimpleResponseDto> dishes = result.getContent().stream()
                .map(DishSimpleDtoMapper::toDto)
                .toList();

        var pagination = new PagedDishResponseDto.PaginationInfo(
                result.getTotalPages(),
                result.getTotalElements(),
                result.getPageSize(),
                result.getCurrentPage(),
                result.isFirst(),
                result.isLast(),
                result.hasNext(),
                result.hasPrevious()
        );

        return new PagedDishResponseDto(dishes, pagination);
    }
}
