package pl.alex.order.create.domain;

import java.math.BigDecimal;

public record OrderCreatedEvent(String name,
                                BigDecimal price,
                                Integer quantity,
                                OrderStatus status) {

    public static OrderCreatedEvent from(Order order) {
        return new OrderCreatedEvent(
                order.name(),
                order.price(),
                order.quantity(),
                order.status()
        );
    }
}
