package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.impl.OrderServiceImpl;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/orders")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final JwtUtil jwtUtil;

    public OrderController(OrderServiceImpl orderService, JwtUtil jwtUtil) {
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @PathVariable Long restaurantId,
            @RequestBody CreateOrderRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long clientId = jwtUtil.extractUserId(token);

        Order createdOrder = orderService.createOrder(restaurantId,clientId, dto);
        return ResponseEntity.ok(createdOrder);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping
    public ResponseEntity<PagedOrderResponseDto> listOrdersByState(
            @PathVariable Long restaurantId,
            @RequestParam String state,
            @RequestParam int page,
            @RequestParam int size,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(orderService.listOrdersByState(restaurantId,employeeId, state, page, size));
    }
}