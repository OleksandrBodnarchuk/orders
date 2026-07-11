package pl.alex.order.create.domain.event;

import pl.alex.order.create.domain.Order;
import pl.alex.order.create.domain.OrderStatus;

import java.math.BigDecimal;

public record OrderCreatedEvent(String id,
                                String name,
                                BigDecimal price,
                                Integer quantity,
                                OrderStatus status) {

    public static OrderCreatedEvent from(Order order) {
        return new OrderCreatedEvent(
                order.id().toString(),
                order.name(),
                order.price(),
                order.quantity(),
                order.status()
        );
    }
}
