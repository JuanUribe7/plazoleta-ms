package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.PagedRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantService;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final JwtUtil jwtUtil;


    public RestaurantController(RestaurantService restaurantService, JwtUtil jwtUtil) {
        this.restaurantService = restaurantService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(
            @RequestBody RestaurantRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        jwtUtil.extractUserId(token);
        return ResponseEntity.ok(restaurantService.createRestaurant(dto));
    }

    @PreAuthorize("hasRole('OWNER')")
    @GetMapping("/{restaurantId}/exists")
    public ResponseEntity<Void> validateRestaurantExists(@PathVariable Long restaurantId) {
        restaurantService.validateExists(restaurantId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/{restaurantId}/employees/{employeeId}")
    public ResponseEntity<Void> assignEmployee(
            @PathVariable Long restaurantId,
            @PathVariable Long employeeId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long ownerId = jwtUtil.extractUserId(token);
        restaurantService.assignEmployee(restaurantId, employeeId, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping()
    public ResponseEntity<PageResponseDto<RestaurantBasicResponseDto>> listRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(restaurantService.listRestaurants(page, size));
    }

    @GetMapping("/{ownerId}/employees")
    public ResponseEntity<List<Long>> getEmployeeIds(@PathVariable Long ownerId) {
        return ResponseEntity.ok(restaurantService.getEmployeeIdsByOwner(ownerId));
    }
}
