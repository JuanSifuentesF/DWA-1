package pe.edu.cibertec.appservicefinanzas.remoteservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "app-service-academico")
public interface ServiceDocenteClient {

  @GetMapping("/docente")
  String getDocente();



}
