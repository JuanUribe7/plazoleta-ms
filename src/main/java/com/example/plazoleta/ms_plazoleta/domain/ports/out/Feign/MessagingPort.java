package com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign;

import com.example.plazoleta.ms_plazoleta.application.dto.request.SmsRequestDto;

public interface MessagingPort {
    void sendSms(SmsRequestDto dto);
}