package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantServiceHandler restaurantServiceHandler;


    public RestaurantController(
            RestaurantServiceHandler restaurantServiceHandler
    ) {
        this.restaurantServiceHandler = restaurantServiceHandler;


    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(
            @RequestBody RestaurantRequestDto dto
    ) {
        return  ResponseEntity.ok(restaurantServiceHandler.createRestaurant(dto));
    }


}
