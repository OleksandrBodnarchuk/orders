package pl.alex.order.query.adapters.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.alex.order.create.adapters.out.persistence.OrderRepository;
import pl.alex.order.query.api.OrderResponse;
import pl.alex.order.query.ports.OrderQueryRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JpaOrderQueryRepositoryAdapter implements OrderQueryRepositoryPort {

    OrderRepository orderRepository;

    @Override
    public Optional<OrderResponse> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(entity -> OrderResponse.from(entity.toOrder()));
    }

    // TODO: add pagination
    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(entity -> OrderResponse.from(entity.toOrder()))
                .toList();
    }
}
