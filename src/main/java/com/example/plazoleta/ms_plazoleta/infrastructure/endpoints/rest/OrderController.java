package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.services.impl.OrderServiceImpl;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.infrastructure.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceImpl orderService;
    private final JwtUtil jwtUtil;

    public OrderController(OrderServiceImpl orderService, JwtUtil jwtUtil) {
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody CreateOrderRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long clientId = jwtUtil.extractUserId(token);

        Order createdOrder = orderService.createOrder(clientId, dto);
        return ResponseEntity.ok(createdOrder);
    }
}