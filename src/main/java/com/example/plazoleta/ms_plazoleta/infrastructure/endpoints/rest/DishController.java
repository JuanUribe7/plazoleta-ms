package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishServiceHandler dishServiceHandler;
    public DishController(DishServiceHandler dishServiceHandler) {
        this.dishServiceHandler = dishServiceHandler;
    }

    @PostMapping
    public ResponseEntity<DishResponseDto> createDish(@PathVariable Long restaurantId, @RequestBody DishRequestDto dto, @RequestHeader Long ownerId) {
        dto.setRestaurantId(restaurantId);
        dto.setOwnerId(ownerId);

        return ResponseEntity.ok(dishServiceHandler.createDish(dto));
    }

    @PatchMapping("/{dishId}")
    public ResponseEntity<UpdateDishResponseDto> updateDish(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @RequestBody UpdateDishRequestDto dto,
            @RequestHeader Long ownerId
    ) {
        dto.setDishId(dishId);
        dto.setRestaurantId(restaurantId);
        dto.setOwnerId(ownerId);
        return ResponseEntity.ok(dishServiceHandler.updateDish(dto, dishId) );
    }
}
