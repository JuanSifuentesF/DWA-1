package pe.edu.cibertec.demosecurityjwt.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idusuario;
  private String nomusuario;
  private String nombres;
  private String apellidos;
  private String email;
  private String password;
  private Boolean activo;

  @ManyToMany(
        cascade = CascadeType.MERGE,
        fetch = FetchType.EAGER
  )
  @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "idusuario"),
        inverseJoinColumns = @JoinColumn(name = "idrol")
  )
  private Set<Rol> roles;//Set Evita que tengamos duplicados

}
