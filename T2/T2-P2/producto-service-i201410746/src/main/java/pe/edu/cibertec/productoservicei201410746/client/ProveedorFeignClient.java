package pe.edu.cibertec.productoservicei201410746.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "proveedor-service-i201410746")
public interface ProveedorFeignClient {
  @GetMapping("/proveedores")
  String obtenerMensajeProveedor();
}