package pl.alex.order.create.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "outbox")
@BatchSize(size = 30)
public class OutboxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID aggregateId;

    private String aggregateName;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    @PostUpdate
    public void postUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
