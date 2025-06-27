package pe.edu.cibertec.demosecurityjwt.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.demosecurityjwt.model.Usuario;
import pe.edu.cibertec.demosecurityjwt.repository.UsuarioRepository;
import pe.edu.cibertec.demosecurityjwt.service.IUsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public Usuario obtenerUsuarioXNomusuario(String nomUsuario) {
    return usuarioRepository.findByNomusuario(nomUsuario);
  }
}
