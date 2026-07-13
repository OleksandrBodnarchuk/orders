package pl.alex.order.create.application.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alex.order.create.application.ports.out.OrderRepositoryPort;
import pl.alex.order.create.domain.Order;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateOrderCommandHandler {

    OrderRepositoryPort jpaOrderRepositoryAdapter;

    @Transactional
    public void handle(CreateOrderCommand command) {
        jpaOrderRepositoryAdapter.save(Order.from(command));
    }
}
