
package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService;
    private final JwtUtil jwtUtil;

    public DishController(DishService dishService, JwtUtil jwtUtil) {
        this.dishService = dishService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<DishResponseDto> createDish(
            @PathVariable Long restaurantId,
            @RequestBody DishRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        DishResponseDto response = dishService.createDish(dto, restaurantId, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{dishId}/status")
    public ResponseEntity<Void> changeDishStatus(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @RequestParam boolean active,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        dishService.changeDishStatus(dishId, restaurantId, ownerId, active);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{dishId}")
    public ResponseEntity<DishResponseDto> updateDish(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @RequestBody UpdateDishRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);

        dto.setRestaurantId(restaurantId);
        dto.setDishId(dishId);
        return ResponseEntity.ok(dishService.updateDish(dto, ownerId));
    }
}

