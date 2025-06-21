package pe.edu.cibertec.kafkapublicador.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublicadorService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private static final String TOPIC = "topic-app-web";

  public KafkaPublicadorService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void enviarMensaje(String mensaje){
    this.kafkaTemplate.send(TOPIC, mensaje);
  }

}
