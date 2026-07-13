package pl.alex.order.create.domain;

import lombok.Builder;
import pl.alex.order.create.application.command.CreateOrderCommand;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record Order(UUID id, String name, BigDecimal price, Integer quantity, OrderStatus status) {

    public static Order from(CreateOrderCommand command) {
        return new Order(UUID.randomUUID(), command.name(), command.price(), command.quantity(), OrderStatus.CREATED);
    }
}
