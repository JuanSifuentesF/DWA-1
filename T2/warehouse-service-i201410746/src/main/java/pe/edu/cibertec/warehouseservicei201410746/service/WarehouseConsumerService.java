package pe.edu.cibertec.warehouseservicei201410746.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.warehouseservicei201410746.model.Order;

@Service
public class WarehouseConsumerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseConsumerService.class);

  @KafkaListener(topics = "orders", groupId = "warehouse-group")
  public void consume(Order order) {
    LOGGER.info(String.format("Almacén: Pedido %s listo para preparación", order.getOrderId()));
  }
}