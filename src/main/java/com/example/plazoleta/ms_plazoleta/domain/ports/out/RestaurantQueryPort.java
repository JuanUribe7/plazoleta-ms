package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

public interface RestaurantQueryPort {
    PaginatedResult<Restaurant> findAllSortedByName(int page, int size);
}
