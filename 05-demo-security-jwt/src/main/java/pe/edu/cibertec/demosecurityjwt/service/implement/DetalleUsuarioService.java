package pe.edu.cibertec.demosecurityjwt.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.demosecurityjwt.model.Rol;
import pe.edu.cibertec.demosecurityjwt.model.Usuario;
import pe.edu.cibertec.demosecurityjwt.service.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DetalleUsuarioService implements UserDetailsService {

  private final IUsuarioService usuarioService;

  // Esta interfaz es una de Spring Security
  // Este método se ejecuta una vez la autenticación se ejecuta de manera correcta.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioService.obtenerUsuarioXNomusuario(username);
    if (usuario != null) {
      return getUserDetails(usuario, getAuthorities(usuario.getRoles()));
    }
    throw new UsernameNotFoundException("Usuario no encontrado");
  }

  //GrantedAuthority, este objeto de Spring Security
  public List<GrantedAuthority> getAuthorities(Set<Rol> roles) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (Rol role : roles) {
      authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getNomrol()));
    }
    return authorities;
  }

  // Este método indica si el usuario va a estar habilitado, con el valor getActivo
  private UserDetails getUserDetails(Usuario usuario,
                                     List<GrantedAuthority> authorities){
    return new User(usuario.getNomusuario(),
          usuario.getPassword(), usuario.getActivo(),
          true,true,
          true,authorities);
  }
}
