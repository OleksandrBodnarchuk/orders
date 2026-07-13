package pl.alex.order.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaConfig {

    OrdersTopicConfiguration ordersTopicConfiguration;
    ProducerConfiguration producerConfiguration;

    private Map<String, Object> producerConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerConfiguration.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, producerConfiguration.getAcks());
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, producerConfiguration.getDeliveryTimeoutMs());
        config.put(ProducerConfig.LINGER_MS_CONFIG, producerConfiguration.getLingerMs());
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, producerConfiguration.getRequestTimeoutMs());
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, producerConfiguration.getEnnableIdempotence());
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, producerConfiguration.getMaximumInFlightRequestsPerConnection());
        config.put(JacksonJsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return config;
    }

    @Bean
    ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(ordersTopicConfiguration.getName())
                .partitions(ordersTopicConfiguration.getPartitions())
                .replicas(ordersTopicConfiguration.getReplicas())
                .build();
    }


}
