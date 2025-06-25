package pe.edu.cibertec.proveedorservicei201410746.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

  @GetMapping
  public String obtenerMensajeProveedor() {
    return "Mensaje desde el proveedor: El producto fue validado y tiene stock disponible.";
  }
}