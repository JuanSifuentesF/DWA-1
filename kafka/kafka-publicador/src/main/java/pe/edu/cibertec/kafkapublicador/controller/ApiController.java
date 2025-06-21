package pe.edu.cibertec.kafkapublicador.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.kafkapublicador.service.KafkaPublicadorService;

@Controller
@RequestMapping("/api")
public class ApiController {

  private final KafkaPublicadorService kafkaPublicadorService;

  public ApiController(KafkaPublicadorService kafkaPublicadorService) {
    this.kafkaPublicadorService = kafkaPublicadorService;
  }

  @RequestMapping("/enviar")
  public ResponseEntity<String> enviar(@RequestParam String mensaje) {
    this.kafkaPublicadorService.enviarMensaje(mensaje);
    return new ResponseEntity<>("Mensaje enviado correctamente a Kafka",
          HttpStatus.OK);
  }

}
