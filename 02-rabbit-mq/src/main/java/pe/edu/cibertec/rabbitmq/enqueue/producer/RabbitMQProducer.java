package pe.edu.cibertec.rabbitmq.enqueue.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQProducer {

  private final RabbitTemplate rabbitTemplate;
  private static final String EXCHANGE_NAME = "prueba_exchange";
  private static final String ROUTING_KEY = "prueba_routing";

  public void enviarMensaje(String mensaje){
    rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, mensaje);
    log.info("Mensaje enviado a RabbitMQ");
  }

}
