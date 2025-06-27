package pe.edu.cibertec.demosecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.demosecurityjwt.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

  Usuario findByNomusuario(String nomUsuario);

}
