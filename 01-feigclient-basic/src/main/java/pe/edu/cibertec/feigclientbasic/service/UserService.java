package pe.edu.cibertec.feigclientbasic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.UserClient;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model.User;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserClient userClient;

  public List<User> listarUsuarios(){
    return userClient.listaUsuarios();
  }

  public User obtenerUsuario(Long id){
    return userClient.obtenerUsuario(id);
  }

}
