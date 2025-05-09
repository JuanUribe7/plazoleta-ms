package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.CreateRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.restaurant.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.CreateRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.AssignEmployeeServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.ListRestaurantsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.ValidateRestaurantExistsServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final CreateRestaurantServicePort createRestaurantService;
    private final AssignEmployeeServicePort assignEmployeeService;
    private final ListRestaurantsServicePort listRestaurantsService;
    private final ValidateRestaurantExistsServicePort validateRestaurantService;

    @Override
    public CreateRestaurantResponseDto createRestaurant(RestaurantRequestDto dto) {
        Restaurant restaurant = RestaurantDtoMapper.toModel(dto);
        Restaurant response = createRestaurantService.execute(restaurant);
        return RestaurantDtoMapper.toResponseDto(response);
    }

    @Override
    public void validateExists(Long restaurantId) {
        validateRestaurantService.validate(restaurantId);
    }

    @Override
    public List<Long> getEmployeeIdsByOwner(Long ownerId) {
        return validateRestaurantService.getEmployeeIdsByOwner(ownerId);
    }

    @Override
    public void assignEmployee(Long restaurantId, Long employeeId, Long ownerId) {
        assignEmployeeService.execute(restaurantId, employeeId, ownerId);
    }

    @Override
    public PageResponseDto<RestaurantBasicResponseDto> listRestaurants(int page, int size) {
        PaginatedResult<Restaurant> result = listRestaurantsService.listRestaurants(page, size);

        List<RestaurantBasicResponseDto> content = result.getContent().stream()
                .map(restaurant -> new RestaurantBasicResponseDto(
                        restaurant.getName(),
                        restaurant.getUrlLogo()
                ))
                .toList();

        return new PageResponseDto<>(
                content,
                result.getPage(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.getPage() == 0,
                result.getPage() == result.getTotalPages() - 1,
                result.getPage() < result.getTotalPages() - 1,
                result.getPage() > 0
        );
    }
}

