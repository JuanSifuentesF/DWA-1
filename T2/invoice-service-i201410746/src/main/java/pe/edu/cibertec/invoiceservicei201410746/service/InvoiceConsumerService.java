package pe.edu.cibertec.invoiceservicei201410746.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.invoiceservicei201410746.model.Order;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class InvoiceConsumerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceConsumerService.class);

  @KafkaListener(topics = "orders", groupId = "invoice-group")
  public void consume(Order order) {
    LOGGER.info(String.format("Factura generada para el pedido %s", order.getOrderId()));
  }


}
