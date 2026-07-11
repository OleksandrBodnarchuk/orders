package pl.alex.order.query.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.alex.order.query.api.OrderResponse;
import pl.alex.order.query.ports.OrderQueryRepositoryPort;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetOrderQueryHandler {

    OrderQueryRepositoryPort orderQueryRepositoryPort;

    public OrderResponse handle(OrderQuery query) {
        return orderQueryRepositoryPort.getOrderById(query.orderId())
                .orElseThrow(() -> new RuntimeException("Order Not Found"));
    }

    public List<OrderResponse> handle() {
        return orderQueryRepositoryPort.getAllOrders();
    }
}
