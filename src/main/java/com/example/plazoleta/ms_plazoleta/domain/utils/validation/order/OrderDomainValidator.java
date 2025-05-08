package com.example.plazoleta.ms_plazoleta.domain.utils.validation.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.AlreadyExistsException;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;

import java.util.List;

public class OrderDomainValidator {
    private static final List<OrderStatus> ACTIVE_STATUSES = List.of(
            OrderStatus.PENDING,
            OrderStatus.PREPARING,
            OrderStatus.READY
    );

    private OrderDomainValidator() {}

    public static void validateClientHasNoActiveOrder(Long clientId, OrderPersistencePort port) {
        if (port.existsByClientIdAndStatuses(clientId, ACTIVE_STATUSES)) {
            throw new AlreadyExistsException(ExceptionMessages.CLIENT_ALREADY_HAVE_ORDER);
        }
    }
}
