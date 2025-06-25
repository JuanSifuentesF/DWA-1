package pe.edu.cibertec.productoservicei201410746;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductoServiceI201410746Application {

  public static void main(String[] args) {
    SpringApplication.run(ProductoServiceI201410746Application.class, args);
  }

}
