package pl.alex.order.query.ports;

import pl.alex.order.query.api.OrderResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderQueryRepositoryPort {

    Optional<OrderResponse> getOrderById(UUID orderId);

    List<OrderResponse> getAllOrders();
}
