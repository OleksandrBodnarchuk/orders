package pl.alex.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "kafka.producer")
public class ProducerConfiguration {

    private String bootstrapServers;
    private String acks;
    private Integer deliveryTimeoutMs;
    private Integer lingerMs;
    private Integer requestTimeoutMs;
    private Boolean ennableIdempotence;
    private Integer retries;
    private Integer maximumInFlightRequestsPerConnection;
}