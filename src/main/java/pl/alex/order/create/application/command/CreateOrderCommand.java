package pl.alex.order.create.application.command;

import pl.alex.order.create.api.CreateOrderRequest;

import java.math.BigDecimal;

public record CreateOrderCommand(String name, BigDecimal price, Integer quantity) {

    public static CreateOrderCommand from(CreateOrderRequest createOrderRequest) {
        return new CreateOrderCommand(createOrderRequest.name(),
                createOrderRequest.price(),
                createOrderRequest.quantity()
        );
    }
}
