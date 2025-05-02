package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.OrderDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.OrderService;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Order.AssignOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Order.CreateOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Order.ListOrdersByStateServicePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.order.StatusValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CreateOrderServicePort createOrderServicePort;
    private final ListOrdersByStateServicePort listOrdersByStateServicePort;
    private final AssignOrderServicePort assignOrderServicePort;



    public Order createOrder(Long restaurantId,Long clientId, CreateOrderRequestDto dto) {
        Order order = OrderDtoMapper.toModel(restaurantId ,clientId, dto);
        return createOrderServicePort.createOrder(order);
    }

    public PagedOrderResponseDto listOrdersByState(
            Long restaurantId,
            Long employeeId,
            String status,
            int page,
            int size
    ) {
        Optional<OrderStatus> statusOpt = Optional.empty();

        if (status != null && !status.trim().isEmpty()) {
            statusOpt = Optional.of(StatusValidator.validate(status));
        }
        Pagination pagination = new Pagination(page, size);
        PagedResult<Order> result = listOrdersByStateServicePort.listOrdersByState(restaurantId ,employeeId, statusOpt, pagination);
        return OrderDtoMapper.toDto(result);
    }

    @Override
    public OrderResponseDto assignOrder(Long restaurantId, Long orderId, Long employeeId) {
        Order updated = assignOrderServicePort.assignToOrder(restaurantId, orderId, employeeId);
        return OrderDtoMapper.toDto(updated);
    }
}