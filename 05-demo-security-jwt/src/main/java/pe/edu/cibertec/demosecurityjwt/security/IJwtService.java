package pe.edu.cibertec.demosecurityjwt.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import pe.edu.cibertec.demosecurityjwt.model.Usuario;

import java.util.List;

public interface IJwtService {


  String generarToken(Usuario usuario, List<GrantedAuthority> authorities);
  Claims parseClaims(String token); //Es de jsonwebtoken
  boolean tokenValido(String token);
  String extraerToken(HttpServletRequest request);
  void setAutenticacion(Claims claims);

}
