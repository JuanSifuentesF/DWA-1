package pe.edu.cibertec.demosecurityjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.demosecurityjwt.model.Rol;

public interface RolRepository extends JpaRepository<Rol,Integer> {

  // Usaremos funciones en línea JPA, definimos un nombre realizamos una consulta
  // select * from rol where nomrol = ?
  Rol findByNomrol(String nomrol);
}
