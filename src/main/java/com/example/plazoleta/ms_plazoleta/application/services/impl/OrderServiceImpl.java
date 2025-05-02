package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.OrderDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateOrderServicePort;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    private final CreateOrderServicePort createOrderServicePort;

    public OrderServiceImpl(CreateOrderServicePort createOrderServicePort) {
        this.createOrderServicePort = createOrderServicePort;
    }

    public Order createOrder(Long clientId, CreateOrderRequestDto dto) {
        Order order = OrderDtoMapper.toModel(clientId, dto);
        return createOrderServicePort.createOrder(order);
    }
}