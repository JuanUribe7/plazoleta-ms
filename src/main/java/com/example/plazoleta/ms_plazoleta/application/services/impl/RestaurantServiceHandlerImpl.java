package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantSimpleDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceHandlerImpl implements RestaurantServiceHandler {


    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoMapper mapper;
    private final RestaurantSimpleDtoMapper mapperSimple;



    public RestaurantServiceHandlerImpl(IRestaurantServicePort restaurantServicePort,
                                        @Qualifier("restaurantDtoMapperImpl") RestaurantDtoMapper mapper,
                                        @Qualifier("restaurantSimpleDtoMapperImpl") RestaurantSimpleDtoMapper mapperSimple) {

        this.restaurantServicePort = restaurantServicePort;
        this.mapper = mapper;
        this.mapperSimple = mapperSimple;

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
    @Override
    public Page<RestaurantSimpleResponseDto> getAllRestaurantsPaged(int page, int size) {
        Page<Restaurant> restaurantPage = restaurantServicePort.getAllRestaurantsPaged(page, size);
        return restaurantPage.map(mapperSimple::toSimpleDto);
    }

    @Override
    public boolean isOwnerOfRestaurant(Long restaurantId, Long ownerId) {
        return restaurantServicePort.isOwnerOfRestaurant(restaurantId, ownerId);
    }

}
