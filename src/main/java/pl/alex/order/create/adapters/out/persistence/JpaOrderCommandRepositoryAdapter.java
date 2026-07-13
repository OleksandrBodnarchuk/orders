package pl.alex.order.create.adapters.out.persistence;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.alex.order.create.adapters.out.persistence.entity.OrderEntity;
import pl.alex.order.create.adapters.out.persistence.entity.OutboxEntity;
import pl.alex.order.create.adapters.out.persistence.entity.OutboxStatus;
import pl.alex.order.create.application.ports.out.OrderRepositoryPort;
import pl.alex.order.create.domain.Order;
import pl.alex.order.create.domain.OrderCreatedEvent;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JpaOrderCommandRepositoryAdapter implements OrderRepositoryPort {

    OrderRepository orderRepository;
    OutboxRepository outboxRepository;
    ObjectMapper objectMapper;

    @Override
    public void save(Order order) {
        OrderEntity entity = OrderEntity.from(order);
        entity = orderRepository.save(entity);

        OutboxEntity outboxEntity = new OutboxEntity();
        outboxEntity.setAggregateId(entity.getId());
        outboxEntity.setAggregateName("order-created");
        outboxEntity.setPayload(objectMapper.writeValueAsString(OrderCreatedEvent.from(order)));
        outboxEntity.setStatus(OutboxStatus.NEW);
        outboxEntity.setCreatedAt(LocalDateTime.now());
        outboxRepository.save(outboxEntity);
    }
}
