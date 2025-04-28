package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishServiceHandler dishServiceHandler;
    private final JwtUtil jwtUtil;

    public DishController(DishServiceHandler dishServiceHandler, JwtUtil jwtUtil) {
        this.dishServiceHandler = dishServiceHandler;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    public ResponseEntity<DishResponseDto> createDish(
            @PathVariable Long restaurantId,
            @RequestBody DishRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        dto.setRestaurantId(restaurantId);
        dto.setOwnerId(ownerId);

        return ResponseEntity.ok(dishServiceHandler.createDish(dto));
    }

    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{dishId}")
    public ResponseEntity<UpdateDishResponseDto> updateDish(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @RequestBody UpdateDishRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        dto.setRestaurantId(restaurantId);
        dto.setOwnerId(ownerId);
        return ResponseEntity.ok(dishServiceHandler.updateDish(dto, dishId));
    }
}
