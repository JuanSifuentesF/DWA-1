package pe.edu.cibertec.feigclientbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeigclientBasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeigclientBasicApplication.class, args);
  }

}
