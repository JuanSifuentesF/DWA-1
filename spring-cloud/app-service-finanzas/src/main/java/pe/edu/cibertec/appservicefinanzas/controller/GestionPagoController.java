package pe.edu.cibertec.appservicefinanzas.controller;

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

  @GetMapping("/pago-docente")
  public String realizarPagoDocente(){
    return serviceDocenteClient.getDocente();
  }


}
