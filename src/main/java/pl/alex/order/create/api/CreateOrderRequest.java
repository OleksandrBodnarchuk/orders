package pl.alex.order.create.api;

import java.math.BigDecimal;

public record CreateOrderRequest(String name, BigDecimal price, Integer quantity) {
}
