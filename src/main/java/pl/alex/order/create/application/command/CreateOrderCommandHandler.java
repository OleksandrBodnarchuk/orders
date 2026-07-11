package pl.alex.order.create.application.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.alex.order.create.application.ports.out.OrderEventPublisherPort;
import pl.alex.order.create.application.ports.out.OrderRepositoryPort;
import pl.alex.order.create.domain.Order;
import pl.alex.order.create.domain.event.OrderCreatedEvent;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateOrderCommandHandler {

    OrderRepositoryPort orderRepositoryPort;
    OrderEventPublisherPort orderEventPublisherPort;

    public void handle(CreateOrderCommand command) {
        val order = Order.from(command);
        log.info("Order created: {}", order);
        orderRepositoryPort.save(order);
        log.info("Sending event to Kafka: {}", order);
        orderEventPublisherPort.publish(OrderCreatedEvent.from(order));
    }
}
