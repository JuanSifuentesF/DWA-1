package pe.edu.cibertec.demosecurityjwt.dto;

import lombok.Data;

@Data
public class UsuarioSeguridadDto {

  private Integer idUsuario;
  private String nomUsuario;
  private String token;
  private String mensajeError;

}
