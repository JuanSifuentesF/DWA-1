package pe.edu.cibertec.orderservicei201410746.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.edu.cibertec.orderservicei201410746.model.Order;
import pe.edu.cibertec.orderservicei201410746.service.OrderProducerService;

@Controller
@RequestMapping("/orders")
public class OrderController {

  private final OrderProducerService orderProducerService;

  public OrderController(OrderProducerService orderProducerService) {
    this.orderProducerService = orderProducerService;
  }

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestBody Order order) {
    orderProducerService.sendOrderCreatedEvent(order);
    return new ResponseEntity<>("Order created and event published successfully.", HttpStatus.CREATED);
  }

}
