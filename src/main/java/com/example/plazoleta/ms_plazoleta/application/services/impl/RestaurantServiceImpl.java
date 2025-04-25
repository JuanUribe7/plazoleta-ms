package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoMapper mapper;


    public RestaurantServiceImpl(IRestaurantServicePort restaurantServicePort,
                                 @Qualifier("restaurantDtoMapperImpl") RestaurantDtoMapper mapper) {

        this.restaurantServicePort = restaurantServicePort;
        this.mapper = mapper;

    }

    @Override
    public RestaurantResponseDto saveRestaurant(RestaurantRequestDto dto) {
        Restaurant restaurant=mapper.toModel(dto);
        Restaurant response=restaurantServicePort.saveRestaurant(restaurant);
        return mapper.toResponseDto(response);
    }


}
