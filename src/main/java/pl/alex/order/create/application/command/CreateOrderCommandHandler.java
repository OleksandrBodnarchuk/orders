package pl.alex.order.create.application.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alex.order.create.application.ports.out.OrderRepositoryPort;
import pl.alex.order.create.domain.Order;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateOrderCommandHandler {

    OrderRepositoryPort jpaOrderRepositoryAdapter;

    @Transactional
    public void handle(CreateOrderCommand command) {
        val order = Order.from(command);
        jpaOrderRepositoryAdapter.save(order);
        log.debug("Order Created Successfully: {}", order);
    }
}
