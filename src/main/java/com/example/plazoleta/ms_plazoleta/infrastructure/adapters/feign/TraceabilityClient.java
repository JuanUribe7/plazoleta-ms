package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.AssignOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CancelOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.order.DeliverOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.order.ReadyOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.trace.OrderTraceabilityRequest;
import com.example.plazoleta.ms_plazoleta.infrastructure.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "traceabilityClient", url = "${traceability.url}", configuration = FeignConfig.class)
public interface TraceabilityClient {

    @PostMapping()
    void registerOrderTrace(@RequestBody OrderTraceabilityRequest request);

    @PutMapping("/assign")
    void assignOrder(@RequestBody AssignOrderRequestDto request);

    @PutMapping("/ready")
    void markAsReady(@RequestBody ReadyOrderRequestDto request);

    @PutMapping("/deliver")
    void deliverOrder(@RequestBody DeliverOrderRequestDto request);

    @PutMapping("/cancel")
    void cancelOrder(@RequestBody CancelOrderRequestDto request);



}



