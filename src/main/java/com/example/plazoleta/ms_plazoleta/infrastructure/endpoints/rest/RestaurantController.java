package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final DishService dishService;


    public RestaurantController(
            RestaurantService restaurantService,
            DishService dishService
    ) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;

    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(
            @RequestBody RestaurantRequestDto dto
    ) {
        return  ResponseEntity.ok(restaurantService.createRestaurant(dto));
    }

    @PostMapping("/{restaurantId}/dish")
    public ResponseEntity<DishResponseDto> createDish(@PathVariable Long restaurantId, @RequestBody DishRequestDto dto, @RequestHeader Long ownerId) {
        dto.setRestaurantId(restaurantId);
        return ResponseEntity.ok(dishService.createDish( restaurantId,dto, ownerId));
    }

}
