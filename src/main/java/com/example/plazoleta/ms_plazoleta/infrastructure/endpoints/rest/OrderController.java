package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.OrderService;
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

@Tag(name = "Orders", description = "Operations related to order management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants/{restaurantId}/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "Create Order", description = "Creates a new order for a restaurant")
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(
            @PathVariable Long restaurantId,
            @RequestBody CreateOrderRequestDto dto,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long clientId = jwtUtil.extractUserId(token);
        orderService.createOrder(dto, restaurantId, clientId);
        return ResponseMessages.ORDER_CREATED;
    }

    @Operation(summary = "Assign Order", description = "Assigns an order to an employee")
    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/{orderId}/assign")
    @ResponseStatus(HttpStatus.OK)
    public String assignOrder(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);
        orderService.assignOrder(restaurantId, orderId, employeeId);
        return ResponseMessages.ORDER_ASSIGNED;
    }

    @Operation(summary = "Mark Order as Ready", description = "Marks an order as ready")
    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/{orderId}/ready")
    @ResponseStatus(HttpStatus.OK)
    public String markOrderAsReady(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);
        orderService.markOrderAsReady(restaurantId, orderId, employeeId);
        return ResponseMessages.ORDER_READY;
    }

    @Operation(summary = "Deliver Order", description = "Delivers an order")
    @PreAuthorize("hasRole('EMPLOYEE')")
    @PatchMapping("/{orderId}/deliver")
    @ResponseStatus(HttpStatus.OK)
    public String deliverOrder(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            @RequestParam String pin,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long employeeId = jwtUtil.extractUserId(token);
        orderService.deliverOrder(restaurantId, orderId, employeeId, pin);
        return ResponseMessages.ORDER_DELIVERED;
    }

    @Operation(summary = "Cancel Order", description = "Cancels an order")
    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/{orderId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public String cancelOrder(
            @PathVariable Long restaurantId,
            @PathVariable Long orderId,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        Long clientId = jwtUtil.extractUserId(token);
        orderService.cancelOrder(restaurantId, orderId, clientId);
        return ResponseMessages.ORDER_CANCELLED;
    }

    @Operation(summary = "List Orders by Status", description = "Lists orders by status for a restaurant")
    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping
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