package pe.edu.cibertec.eurekaserveri201410746;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerI201410746Application {

  public static void main(String[] args) {
    SpringApplication.run(EurekaServerI201410746Application.class, args);
  }

}
