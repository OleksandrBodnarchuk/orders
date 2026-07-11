package pl.alex.order.create.adapters.out.kafka;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import pl.alex.order.config.OrdersTopicConfiguration;
import pl.alex.order.create.domain.event.OrderCreatedEvent;
import pl.alex.order.create.application.ports.out.OrderEventPublisherPort;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaOrderEventPublisherAdapter implements OrderEventPublisherPort {

    KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    OrdersTopicConfiguration ordersTopicConfiguration;

    @Override
    public void publish(OrderCreatedEvent orderCreatedEvent) {
        val orderId = orderCreatedEvent.id();
        try {
            SendResult<String, OrderCreatedEvent> result = kafkaTemplate.send(
                    ordersTopicConfiguration.getName(),
                    orderId, orderCreatedEvent
            ).get();
            log.info("Sent OrderCreatedEvent for orderId={}, result={}", orderId, result);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
