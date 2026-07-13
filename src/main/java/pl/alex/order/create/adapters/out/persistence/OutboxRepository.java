package pl.alex.order.create.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.alex.order.create.adapters.out.persistence.entity.OutboxEntity;
import pl.alex.order.create.adapters.out.persistence.entity.OutboxStatus;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxEntity, UUID> {
    List<OutboxEntity> findByStatus(OutboxStatus status);
}
