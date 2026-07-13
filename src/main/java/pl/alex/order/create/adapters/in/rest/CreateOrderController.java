package pl.alex.order.create.adapters.in.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.alex.order.create.api.CreateOrderRequest;
import pl.alex.order.create.application.command.CreateOrderCommand;
import pl.alex.order.create.application.command.CreateOrderCommandHandler;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateOrderController {

    CreateOrderCommandHandler createOrderCommandHandler;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        createOrderCommandHandler.handle(CreateOrderCommand.from(createOrderRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
