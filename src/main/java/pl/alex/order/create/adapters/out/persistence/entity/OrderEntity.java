package pl.alex.order.create.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alex.order.create.domain.Order;
import pl.alex.order.create.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private BigDecimal price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static OrderEntity from(Order order) {
        return OrderEntity.builder()
                .name(order.name())
                .price(order.price())
                .quantity(order.quantity())
                .status(order.status())
                .build();
    }

    public Order toOrder() {
        return Order.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .status(this.status)
                .build();
    }
}
