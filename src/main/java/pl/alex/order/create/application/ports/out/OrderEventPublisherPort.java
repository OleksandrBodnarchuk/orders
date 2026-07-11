package pl.alex.order.create.application.ports.out;


import pl.alex.order.create.domain.event.OrderCreatedEvent;

public interface OrderEventPublisherPort {

    void publish(OrderCreatedEvent orderCreatedEvent);
}
