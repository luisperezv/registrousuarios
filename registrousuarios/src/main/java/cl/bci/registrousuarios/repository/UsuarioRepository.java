package cl.bci.registrousuarios.repository;

import cl.bci.registrousuarios.repository.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository {

    UsuarioEntity findByCorreo(String correo);
}
