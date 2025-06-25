package pe.edu.cibertec.feigclientbasic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model.User;
import pe.edu.cibertec.feigclientbasic.service.UserService;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  // [/users]
  @GetMapping("/usuarios")
  public ResponseEntity<List<User>> listarUsuarios(){
    return ResponseEntity.ok(userService.listarUsuarios());
  }

  // [/users/1]
  @GetMapping("/usuarios/{id}")
  public ResponseEntity<User> obtenerUsuario(@PathVariable Long id){
    return ResponseEntity.ok(userService.obtenerUsuario(id));
  }




}
