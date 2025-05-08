package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;

public interface RestaurantQueryPort {
    PageResponseDto<RestaurantBasicResponseDto> findAllSortedByName(int page, int size);
}
