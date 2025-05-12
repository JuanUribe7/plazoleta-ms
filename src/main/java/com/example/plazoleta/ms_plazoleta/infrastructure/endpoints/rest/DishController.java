package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.commons.constants.ResponseMessages;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Dishes", description = "Operations related to dish management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/dishes")
public class DishController {

    private final DishService dishService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Create Dish", description = "Creates a new dish for a restaurant")
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createDish(
            @PathVariable Long restaurantId,
            @Valid @RequestBody DishRequestDto dto,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        dishService.createDish(dto, restaurantId, ownerId);
        return ResponseMessages.DISH_CREATED;
    }

    @Operation(summary = "Change Dish Status", description = "Activates or deactivates a dish")
    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{dishId}/status")
    @ResponseStatus(HttpStatus.OK)
    public String changeDishStatus(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @RequestParam boolean active,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        dishService.changeDishStatus(dishId, restaurantId, ownerId, active);
        return ResponseMessages.DISH_STATUS_UPDATED;
    }



    @Operation(summary = "List Dishes", description = "Lists all dishes of a restaurant with optional category filter")
    @ApiResponse(responseCode = "200", description = "Dishes listed successfully")
    @GetMapping
    public ResponseEntity<PageResponseDto<DishResponseDto>> listDishes(
            @PathVariable Long restaurantId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(dishService.listDishes(restaurantId, category, page, size));
    }

    @Operation(summary = "Update Dish", description = "Updates the details of a dish")
    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping("/{dishId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateDish(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @Valid @RequestBody UpdateDishRequestDto dto,
            HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        dto.setRestaurantId(restaurantId);
        dto.setDishId(dishId);
        dishService.updateDish(dto, ownerId);
        return ResponseMessages.DISH_UPDATED;
    }
}