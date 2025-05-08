package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.AssignOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CancelOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.order.DeliverOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.order.ReadyOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.trace.OrderTraceabilityRequest;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import org.springframework.stereotype.Component;

@Component
public class OrderTraceabilityAdapter implements OrderTraceabilityPort {

    private final TraceabilityClient traceabilityClient;

    public OrderTraceabilityAdapter(TraceabilityClient traceabilityClient) {
        this.traceabilityClient = traceabilityClient;
    }

    @Override
    public void registerTrace(Order order) {
        OrderTraceabilityRequest request = new OrderTraceabilityRequest(
                order.getId(),
                order.getClientId()
        );
        traceabilityClient.registerOrderTrace(request);
    }

    @Override
    public void assignOrder(Order order) {
        traceabilityClient.assignOrder(new AssignOrderRequestDto(order.getId(), order.getAssignedEmployeeId()));
    }

    @Override
    public void markAsReady(Order order, String phoneNumber) {
        ReadyOrderRequestDto dto = new ReadyOrderRequestDto(
                order.getId(),
                phoneNumber

        );
        traceabilityClient.markAsReady(dto);
    }

    @Override
    public void deliveOrder(Order order, String pin) {
        DeliverOrderRequestDto dto = new DeliverOrderRequestDto(order.getId(),pin);
        traceabilityClient.deliverOrder(dto);
    }
    @Override
    public void cancelOrder(Order order) {
        CancelOrderRequestDto dto = new CancelOrderRequestDto(order.getId());
        traceabilityClient.cancelOrder(dto);
    }
}
