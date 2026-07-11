package pl.alex.order.query.api;

import pl.alex.order.create.domain.Order;
import pl.alex.order.create.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderResponse(UUID orderId,
                            String name,
                            BigDecimal price,
                            Integer quantity,
                            OrderStatus status) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.id(),
                order.name(),
                order.price(),
                order.quantity(),
                order.status()
        );
    }
}
