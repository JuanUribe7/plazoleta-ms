package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.feign;

import com.example.plazoleta.ms_plazoleta.application.dto.request.SmsRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mensajeriaClient", url = "${mensajeria.url}")
public interface SmsFeignClient {

    @PostMapping("/notifications/sms")
    void sendSms(@RequestBody SmsRequestDto dto);
}