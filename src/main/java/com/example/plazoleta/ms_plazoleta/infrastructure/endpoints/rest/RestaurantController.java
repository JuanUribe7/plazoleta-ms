package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantServiceHandler restaurantServiceHandler;
    private final JwtUtil jwtUtil;

    public RestaurantController(RestaurantServiceHandler restaurantServiceHandler, JwtUtil jwtUtil) {
        this.restaurantServiceHandler = restaurantServiceHandler;
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
        return ResponseEntity.ok(restaurantServiceHandler.createRestaurant(dto));
    }

    @GetMapping
    public ResponseEntity<PagedRestaurantResponseDto> listRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<RestaurantSimpleResponseDto> restaurantsPage = restaurantServiceHandler.getAllRestaurantsPaged(page, size);

        PagedRestaurantResponseDto response = new PagedRestaurantResponseDto();
        response.setRestaurants(restaurantsPage.getContent());
        response.setPagination(new PagedRestaurantResponseDto.PaginationInfo(
                restaurantsPage.getTotalPages(),
                restaurantsPage.getTotalElements()
        ));

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{restaurantId}/owner/{ownerId}")
    public ResponseEntity<Boolean> isOwnerOfRestaurant(@PathVariable Long restaurantId, @PathVariable Long ownerId) {
        boolean isOwner = restaurantServiceHandler.isOwnerOfRestaurant(restaurantId, ownerId);
        return ResponseEntity.ok(isOwner);
    }

}
