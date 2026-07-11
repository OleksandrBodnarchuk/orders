package pl.alex.order.create.adapters.out.persistence;

import org.springframework.stereotype.Service;
import pl.alex.order.create.application.ports.out.OrderRepositoryPort;
import pl.alex.order.create.domain.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InMemOrderRepositoryAdapter implements OrderRepositoryPort {

    public static final Map<UUID, Order> ORDER_HASHMAP = new HashMap<>();

    @Override
    public void save(Order order) {
        ORDER_HASHMAP.put(order.id(), order);
    }
}
