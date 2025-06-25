package pe.edu.cibertec.orderservicei201410746.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.orderservicei201410746.model.Order;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class OrderProducerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducerService.class);
  private static final String TOPIC = "orders";

  private final KafkaTemplate<String, Order> kafkaTemplate;

  public OrderProducerService(KafkaTemplate<String, Order> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendOrderCreatedEvent(Order order) {
    LOGGER.info(String.format("Produciendo mensaje -> %s", order.getOrderId()));
    this.kafkaTemplate.send(TOPIC, order.getOrderId(), order);
  }

}
