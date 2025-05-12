package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.OrderMapper;
import com.example.plazoleta.ms_plazoleta.application.services.OrderService;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;

import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CreateOrderServicePort createOrderServicePort;
    private final ListOrdersByStatusServicePort listOrdersService;
    private final AssignOrderServicePort assignOrderServicePort;
    private final MarkOrderAsReadyServicePort markOrderAsReadyService;
    private final DeliverOrderServicePort deliverService;
    private final CancelOrderServicePort cancelService;



    public Order createOrder(CreateOrderRequestDto dto, Long restaurantId, Long clientId) {
        Order order = OrderMapper.toModel(dto, restaurantId, clientId);
        return createOrderServicePort.createOrder(order);
    }
    @Override
    public void assignOrder(Long restaurantId, Long orderId, Long employeeId) {
        assignOrderServicePort.assignToOrder(restaurantId, orderId, employeeId);

    }

    @Override
    public void markOrderAsReady(Long restaurantId, Long orderId, Long employeeId) {
        markOrderAsReadyService.markAsReady(restaurantId, orderId, employeeId);
    }



    @Override
    public void deliverOrder(Long restaurantId, Long orderId, Long employeeId, String pin) {
         deliverService.deliver(restaurantId, orderId, employeeId, pin);

    }

    @Override
    public void cancelOrder(Long restaurantId, Long orderId, Long clientId) {
        cancelService.cancel(restaurantId, orderId, clientId);

    }

    @Override
    public PageResponseDto<OrderResponseDto> listOrdersByStatus(Long restaurantId, String status, Long employeeId, int page, int size) {
        PaginatedResult<Order> result = listOrdersService.findByStatusAndRestaurant(status, restaurantId, employeeId, page, size);
        List<OrderResponseDto> content = result.getContent().stream()
                .map(OrderMapper::toResponseDto)
                .toList();

        return new PageResponseDto<>(
                content,
                result.getPage(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.getPage() == 0,
                result.getPage() == result.getTotalPages() - 1,
                result.getPage() < result.getTotalPages() - 1,
                result.getPage() > 0
        );
    }




}