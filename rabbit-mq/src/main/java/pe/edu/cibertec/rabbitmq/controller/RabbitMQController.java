package pe.edu.cibertec.rabbitmq.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rabbitmq.enqueue.producer.RabbitMQProducer;

@RequiredArgsConstructor
@RestController
public class RabbitMQController {

  private final RabbitMQProducer producer;

  //localhost:8070/enviar?mensaje=Hola Mundo
  @GetMapping("/enviar")
  public String enviarMensaje(@RequestParam String mensaje){
    producer.enviarMensaje(mensaje);
    return "Mensaje enviado a RabbitMQ";
  }

}
