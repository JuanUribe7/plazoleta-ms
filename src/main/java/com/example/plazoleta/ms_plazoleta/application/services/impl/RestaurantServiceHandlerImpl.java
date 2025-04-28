package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceHandlerImpl implements RestaurantServiceHandler {


    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoMapper mapper;


    public RestaurantServiceHandlerImpl(IRestaurantServicePort restaurantServicePort,
                                        @Qualifier("restaurantDtoMapperImpl") RestaurantDtoMapper mapper) {

        this.restaurantServicePort = restaurantServicePort;
        this.mapper = mapper;

    }

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        Restaurant restaurant=mapper.toModel(dto);
        Restaurant response=restaurantServicePort.createRestaurant(restaurant);
        return mapper.toResponseDto(response);
    }

    @Override
    public boolean validateOwner(Long restaurantId, Long ownerId) {
        Restaurant restaurante = restaurantServicePort.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no existe"));
        return restaurante.getOwnerId().equals(ownerId);
    }


}