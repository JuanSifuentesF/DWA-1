package pe.edu.cibertec.appservicefinanzas.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.appservicefinanzas.remoteservice.ServiceDocenteClient;

@RestController
@RequiredArgsConstructor
public class GestionPagoController {

  private final ServiceDocenteClient serviceDocenteClient;

  @GetMapping("/pago")
  public String realizarPago(){
    return "Respuesta del servicio de Finanzas - Pago";
  }

  // Este método invoca al servicio externo de docentes
  // y utiliza Resilience4j para manejar la lógica de circuit breaker
  // El método se invoca cuando se accede a la ruta /pago-docente
  // Si el servicio externo no está disponible, se invoca el método de fallback
  @GetMapping("/pago-docente")
  @CircuitBreaker(name = "externalService", fallbackMethod = "fallback")
  public String realizarPagoDocente(){
    return serviceDocenteClient.getDocente();
  }

  // Este método se invoca cuando el servicio externo no está disponible
  // Método de fallback para manejar la indisponibilidad del servicio externo
  public String fallback(Exception e) {
    System.out.println("Servicio externo no disponible, error: " + e.getMessage());
    return "Servicio externo no disponible, por favor intente más tarde.";
  }

  // Resumen:
  // - Se define un controlador REST para manejar las solicitudes de pago.
  // - Se inyecta un cliente Feign para comunicarse con el servicio externo de docentes.
  // - Se define un endpoint para realizar pagos y otro para realizar pagos a docentes.
  // - Se utiliza Resilience4j para implementar un circuito breaker que maneja la lógica de fallos.
  // - Se proporciona un método de fallback para manejar la indisponibilidad del servicio externo.
  // - El método de fallback devuelve un mensaje de error amigable al usuario.
  // - El controlador está anotado con @RestController y @RequiredArgsConstructor para la inyección de dependencias.
  // - El método realizarPagoDocente invoca al servicio externo y maneja posibles errores con un fallback.
  // - El método fallback se encarga de registrar el error y devolver un mensaje adecuado al usuario.
  // - El controlador está diseñado para ser parte de un microservicio que maneja la lógica de pagos en una aplicación financiera.

  // Adicionalmente, a veces consideramos que el circuit breaker va a estar en el controlador,
  // pero también puede estar en la capa de servicio. Pero si se aplica en la capa del servicio,
  // los metodos también deben de ser públicos para que el circuito breaker funcione correctamente.

  // Puedo usar este mismo umbral de circuit breaker en otros métodos del controlador
  // cada api puede tener su propio circuito breaker.

}
