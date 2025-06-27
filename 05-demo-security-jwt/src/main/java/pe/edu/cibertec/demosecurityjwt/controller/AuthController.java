package pe.edu.cibertec.demosecurityjwt.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.demosecurityjwt.dto.UsuarioSeguridadDto;
import pe.edu.cibertec.demosecurityjwt.model.Usuario;
import pe.edu.cibertec.demosecurityjwt.security.IJwtService;
import pe.edu.cibertec.demosecurityjwt.service.IUsuarioService;
import pe.edu.cibertec.demosecurityjwt.service.implement.DetalleUsuarioService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

  private final IUsuarioService usuarioService;
  private final DetalleUsuarioService detalleUsuarioService;
  private final AuthenticationManager authenticationManager;
  private final IJwtService jwtService;

  //localhost:8030/api/v1/auth/login
  @GetMapping("/login")
  public ResponseEntity<UsuarioSeguridadDto> login(
        @RequestParam String usuario,
        @RequestParam String password
  ){
    Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(usuario, password));
    if(authentication.isAuthenticated()){
      Usuario objUsuario = usuarioService.obtenerUsuarioXNomusuario(usuario);
      List<GrantedAuthority> authorities =
            detalleUsuarioService.getAuthorities(objUsuario.getRoles());
      String token = jwtService.generarToken(objUsuario, authorities);
      UsuarioSeguridadDto usuarioSeguridadDto = new UsuarioSeguridadDto();
      usuarioSeguridadDto.setToken(token);
      usuarioSeguridadDto.setIdUsuario(objUsuario.getIdusuario());
      usuarioSeguridadDto.setNomUsuario(objUsuario.getNomusuario());
      return new ResponseEntity<>(usuarioSeguridadDto, HttpStatus.OK);
    }
    UsuarioSeguridadDto usuarioSeguridadDto = new UsuarioSeguridadDto();
    usuarioSeguridadDto.setMensajeError("Usuario y/o password incorrectos");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(usuarioSeguridadDto);
  }

}
