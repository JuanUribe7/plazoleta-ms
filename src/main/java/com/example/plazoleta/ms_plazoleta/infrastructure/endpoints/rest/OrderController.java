package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;

import com.example.plazoleta.ms_plazoleta.application.services.OrderService;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    public OrderController(OrderService orderService, JwtUtil jwtUtil) {
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

        Order createdOrder = orderService.createOrder(dto, restaurantId, clientId);
        return ResponseEntity.ok(createdOrder);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/{orderId}/assign")
    public ResponseEntity<OrderResponseDto> assignOrder(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(orderService.assignOrder(restaurantId, orderId, employeeId));
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<OrderResponseDto> markOrderAsReady(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(orderService.markOrderAsReady(restaurantId, orderId, employeeId));
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/{orderId}/deliver")
    public ResponseEntity<OrderResponseDto> deliverOrder(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            @RequestParam String pin,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(orderService.deliverOrder(restaurantId, orderId, employeeId, pin));
    }



    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponseDto> cancelOrder(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long clientId = jwtUtil.extractUserId(token);

        return ResponseEntity.ok(orderService.cancelOrder(restaurantId, orderId, clientId));
    }


    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping()
    public ResponseEntity<PageResponseDto<OrderResponseDto>> listOrdersByStatus(
            @PathVariable Long restaurantId,
            @RequestParam String status,
            @RequestParam int page,
            @RequestParam int size,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);
        return ResponseEntity.ok(orderService.listOrdersByStatus(restaurantId, status, employeeId, page, size));
    }




}