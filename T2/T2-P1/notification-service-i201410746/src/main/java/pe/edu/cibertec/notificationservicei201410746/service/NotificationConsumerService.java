package pe.edu.cibertec.notificationservicei201410746.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.notificationservicei201410746.model.Order;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class NotificationConsumerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumerService.class);

  @KafkaListener(topics = "orders", groupId = "notification-group")
  public void consume(Order order) {
    LOGGER.info(String.format("Correo enviado al cliente por el pedido %s", order.getOrderId()));
  }

}
