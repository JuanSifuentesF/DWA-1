package pe.edu.cibertec.appservicefinanzas.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CircuitBreakerController {

  private final CircuitBreakerRegistry registry;

  @GetMapping("/circuit-status")
  public String circuitStatus() {
    return registry.circuitBreaker("externalService").getState().name();
  }

  // Instrucción para probar el circuito (Bash)
  // for i in {1..10}; do curl -s http://localhost:8081/pago-docente; echo ""; sleep 1; done


}
