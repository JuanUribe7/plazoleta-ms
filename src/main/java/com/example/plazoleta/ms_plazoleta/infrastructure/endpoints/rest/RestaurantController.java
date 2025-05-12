package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.example.plazoleta.ms_plazoleta.commons.constants.ResponseMessages;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurants", description = "Operations related to restaurant management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Create Restaurant", description = "Creates a new restaurant")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createRestaurant(
            @RequestBody RestaurantRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        jwtUtil.extractUserId(token);
        restaurantService.createRestaurant(dto);
        return ResponseMessages.RESTAURANT_CREATED;
    }

    @Operation(summary = "Validate Restaurant Exists", description = "Validates if a restaurant exists")
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/{restaurantId}/exists")
    @ResponseStatus(HttpStatus.OK)
    public String validateRestaurantExists(@PathVariable Long restaurantId) {
        restaurantService.validateExists(restaurantId);
        return ResponseMessages.RESTAURANT_EXISTS;
    }

    @Operation(summary = "Assign Employee", description = "Assigns an employee to a restaurant")
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/{restaurantId}/employees/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String assignEmployee(
            @PathVariable Long restaurantId,
            @PathVariable Long employeeId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        restaurantService.assignEmployee(restaurantId, employeeId, ownerId);
        return ResponseMessages.EMPLOYEE_ASSIGNED;
    }

    @Operation(summary = "Get Employee IDs", description = "Gets the IDs of employees assigned to a restaurant")
    @GetMapping("/{ownerId}/employees")
    public ResponseEntity<List<Long>> getEmployeeIds(@PathVariable Long ownerId) {
        return ResponseEntity.ok(restaurantService.getEmployeeIdsByOwner(ownerId));
    }

    @Operation(summary = "List Restaurants", description = "Lists all restaurants with pagination")
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping
    public ResponseEntity<PageResponseDto<RestaurantBasicResponseDto>> listRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(restaurantService.listRestaurants(page, size));
    }
}