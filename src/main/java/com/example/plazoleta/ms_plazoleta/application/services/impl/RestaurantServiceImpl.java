package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.PagedRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.restaurant.PagedRestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.mappers.restaurant.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant.ListRestaurantsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {


    private final CreateRestaurantServicePort createRestaurantServicePort;
    private final AssignEmployeeServicePort assignEmployeeUseCasePort;
    private final ListRestaurantsServicePort listRestaurantsUseCase;



    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        Restaurant restaurant=RestaurantDtoMapper.toModel(dto);
        Restaurant response= createRestaurantServicePort.execute(restaurant);
        return RestaurantDtoMapper.toResponseDto(response);
    }


    @Override
    public PagedRestaurantResponseDto listRestaurants(int page, int size) {
        Pagination pagination = new Pagination(page, size);
        PagedResult<Restaurant> result = listRestaurantsUseCase.listAll(pagination);
        return PagedRestaurantDtoMapper.toDto(result);
    }

    public void assignEmployee(Long restaurantId, Long employeeId, Long ownerId) {
        assignEmployeeUseCasePort.execute(restaurantId, employeeId, ownerId);
    }

}
