package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import java.util.List;

public class PagedRestaurantDtoMapper {

    private PagedRestaurantDtoMapper() {}

    public static PagedRestaurantResponseDto toDto(PagedResult<Restaurant> result) {
        List<RestaurantSimpleResponseDto> content = result.getContent().stream()
                .map(RestaurantSimpleDtoMapper::toSimpleDto)
                .toList();

        var pagination = new PagedRestaurantResponseDto.PaginationInfo(
                result.getTotalPages(),
                result.getTotalElements(),
                result.getPageSize(),
                result.getCurrentPage(),
                result.isFirst(),
                result.isLast(),
                result.hasNext(),
                result.hasPrevious()
        );

        return new PagedRestaurantResponseDto(content, pagination);
    }
}