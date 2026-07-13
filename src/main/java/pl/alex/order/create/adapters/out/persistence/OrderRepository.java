package pl.alex.order.create.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alex.order.create.adapters.out.persistence.entity.OrderEntity;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
