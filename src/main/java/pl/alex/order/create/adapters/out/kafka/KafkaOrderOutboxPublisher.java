package pl.alex.order.create.adapters.out.kafka;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;
import pl.alex.order.create.adapters.out.persistence.OutboxRepository;
import pl.alex.order.create.adapters.out.persistence.entity.OutboxEntity;
import pl.alex.order.create.adapters.out.persistence.entity.OutboxStatus;
import pl.alex.order.create.domain.OrderCreatedEvent;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaOrderOutboxPublisher {

    OutboxRepository outboxRepository;
    KafkaTemplate<String, Object> kafkaTemplate;
    ObjectMapper objectMapper;
    TransactionTemplate transactionTemplate;

    @Scheduled(fixedDelay = 1000)
    public void publish() {
        List<OutboxEntity> events = outboxRepository.findByStatus(OutboxStatus.NEW);

        if (CollectionUtils.isEmpty(events)) {
            return;
        }

        for (OutboxEntity event : events) {
            try {
                kafkaTemplate.send(
                        String.format("%s-topic", event.getAggregateName()),
                        event.getAggregateId().toString(),
                        objectMapper.readValue(event.getPayload(), OrderCreatedEvent.class)
                ).get();

                updateOutboxStatus(event, OutboxStatus.SENT);
            } catch (Exception e) {
                log.error("Failed to publish event {}", event.getId(), e);
                updateOutboxStatus(event, OutboxStatus.FAILED);
                log.warn("Closing up the cycle to keep data order.");
                break;
            }
        }
    }

    private void updateOutboxStatus(OutboxEntity event, OutboxStatus failed) {
        transactionTemplate.executeWithoutResult(status -> {
            outboxRepository.findById(event.getId())
                    .ifPresent(entity -> {
                        entity.setStatus(failed);
                        outboxRepository.save(entity);
                    });
        });
    }
}
