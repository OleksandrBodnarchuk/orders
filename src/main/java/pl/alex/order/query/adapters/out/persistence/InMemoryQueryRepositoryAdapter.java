package pl.alex.order.query.adapters.out.persistence;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.alex.order.query.api.OrderResponse;
import pl.alex.order.query.ports.OrderQueryRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static pl.alex.order.create.adapters.out.persistence.InMemOrderRepositoryAdapter.ORDER_HASHMAP;

@Service
@RequiredArgsConstructor
public class InMemoryQueryRepositoryAdapter implements OrderQueryRepositoryPort {

    @Override
    public Optional<OrderResponse> getOrderById(UUID orderId) {
        val order = ORDER_HASHMAP.get(orderId);
        if (order == null) {
            return Optional.empty();
        }
        return Optional.of(OrderResponse.from(order));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return ORDER_HASHMAP.values().stream().map(OrderResponse::from).toList();
    }
}
