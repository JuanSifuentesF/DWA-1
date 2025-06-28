package pe.edu.cibertec.demosecurityjwt.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ADMIN')") // Esta anotación permite que solo los usuarios con el rol ADMIN puedan acceder a este endpoint
@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

  @GetMapping("/saludo")
  public ResponseEntity<String> saludoConSecurity() {
    return ResponseEntity.ok("Hola desde Spring Security");
  }

}
