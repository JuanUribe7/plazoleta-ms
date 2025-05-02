package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.OrderDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ListOrdersByStateServicePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.order.StatusValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl {

    private final CreateOrderServicePort createOrderServicePort;
    private final ListOrdersByStateServicePort listOrdersByStateServicePort;

    public OrderServiceImpl(CreateOrderServicePort createOrderServicePort,
                            ListOrdersByStateServicePort listOrdersByStateServicePort) {
        this.createOrderServicePort = createOrderServicePort;
        this.listOrdersByStateServicePort = listOrdersByStateServicePort;
    }

    public Order createOrder(Long clientId, CreateOrderRequestDto dto) {
        Order order = OrderDtoMapper.toModel(clientId, dto);
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
}