package pl.alex.order.create.application.ports.out;

import pl.alex.order.create.domain.Order;

public interface OrderRepositoryPort {

    void save(Order order);

}
