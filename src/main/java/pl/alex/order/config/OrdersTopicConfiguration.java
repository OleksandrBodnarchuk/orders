package pl.alex.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "kafka.topic.orders")
public class OrdersTopicConfiguration {

    private String name;
    private Integer partitions;
    private Integer replicas;

}
