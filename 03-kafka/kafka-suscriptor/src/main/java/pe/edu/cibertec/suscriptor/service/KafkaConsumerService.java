package pe.edu.cibertec.suscriptor.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

  @KafkaListener(topics = "topic-app-web",groupId = "group-app-web")
  public void subscribe(String mensaje) {
    System.out.println("Mensaje recibido desde kafka: " + mensaje);
  }

}
