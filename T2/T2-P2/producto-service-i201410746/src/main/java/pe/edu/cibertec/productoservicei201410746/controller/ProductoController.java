package pe.edu.cibertec.productoservicei201410746.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.productoservicei201410746.client.ProveedorFeignClient;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

  private final ProveedorFeignClient proveedorFeignClient;

  @GetMapping
  public ResponseEntity<String> obtenerProductos() {
    return new ResponseEntity<>("Respuesta del servicio de Productos (GET)", HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<String> registrarProducto() {
    String mensajeDelProveedor = proveedorFeignClient.obtenerMensajeProveedor();
    String respuestaFinal = "Producto registrado con éxito. " + mensajeDelProveedor;

    return new ResponseEntity<>(respuestaFinal, HttpStatus.CREATED);
  }
}
