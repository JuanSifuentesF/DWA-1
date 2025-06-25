package pe.edu.cibertec.feigclientbasic.clientrest.placeholder;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model.User;

import java.util.List;


@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com")
public interface UserClient {

  // [/users]
  @GetMapping("/users")
  List<User> listaUsuarios();

  // [/users/1]
  @GetMapping("/users/{id}")
  User obtenerUsuario(@PathVariable Long id);


}
