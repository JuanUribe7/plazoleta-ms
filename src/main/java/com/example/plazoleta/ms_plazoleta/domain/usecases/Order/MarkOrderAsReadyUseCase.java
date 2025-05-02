package com.example.plazoleta.ms_plazoleta.domain.usecases.Order;

import com.example.plazoleta.ms_plazoleta.application.dto.request.SmsRequestDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Order.MarkOrderAsReadyServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign.MessagingPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.OrderValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.PinGenerator;


public class MarkOrderAsReadyUseCase implements MarkOrderAsReadyServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final MessagingPort messagingPort;
    private final UserValidationPort userValidationPort;

    public MarkOrderAsReadyUseCase(OrderPersistencePort orderPersistencePort,
                                   RestaurantPersistencePort restaurantPersistencePort,
                                   UserValidationPort userValidationPort,
                                   MessagingPort messagingPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userValidationPort = userValidationPort;
        this.messagingPort = messagingPort;
    }

    @Override
    public Order markAsReady(Long restaurantId, Long orderId, Long employeeId) {
        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        OrderValidator.validateAccessAndStatus(order, restaurantId, employeeId, restaurantPersistencePort);

        String pin = PinGenerator.generate();
        Order updated = order.markAsReady(pin);
        Order saved = orderPersistencePort.saveOrder(updated);

        String phoneNumber = userValidationPort.getPhoneByUserId(saved.getClientId());

        messagingPort.sendSms(new SmsRequestDto(phoneNumber, "Your order is ready! PIN: " + pin));

        return saved;
    }
}