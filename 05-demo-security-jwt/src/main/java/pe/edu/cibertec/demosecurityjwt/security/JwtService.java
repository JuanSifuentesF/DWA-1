package pe.edu.cibertec.demosecurityjwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.demosecurityjwt.model.Usuario;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService implements IJwtService{

  private static final String SECRET = "mi-clave-secreta-super-larga-y-segura-1234567890";
  private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

  @Override
  public String generarToken(Usuario usuario, List<GrantedAuthority> authorities) {
    return Jwts.builder()
          .id(usuario.getIdusuario().toString())
          .subject(usuario.getNomusuario())
          .claim("Authorities",
                authorities.stream().map(GrantedAuthority::getAuthority)
                      .collect(Collectors.toList()))
          .issuedAt(new Date())
          .expiration(new Date(System.currentTimeMillis()+300000))
          .signWith(KEY)
          .compact();
  }

  @Override
  public Claims parseClaims(String token) {
    return Jwts.parser().verifyWith(
          (SecretKey) KEY)
          .build()
          .parseSignedClaims(token)
          .getPayload();
  }

  @Override
  public boolean tokenValido(String token) {
    try{
      parseClaims(token);
      return true;
    } catch (JwtException e){
      return false;
    }
  }

  @Override
  public String extraerToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if(header != null && header.startsWith("Bearer ")){
      return header.substring(7);
    }
    return null;
  }

  @Override
  public void setAutenticacion(Claims claims) {
    List<String> authorities = claims.get("Authorities", List.class);
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          claims.getSubject(),null,
          authorities.stream().map(SimpleGrantedAuthority::new).toList()
    );
    SecurityContextHolder.getContext().setAuthentication(auth);
  }
}
