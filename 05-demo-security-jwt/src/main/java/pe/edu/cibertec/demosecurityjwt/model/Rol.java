package pe.edu.cibertec.demosecurityjwt.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rol")
public class Rol {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idrol;
  private String nomrol;

}
