package io.github.wagmedrado.agendaevento.repository;

import io.github.wagmedrado.agendaevento.model.acesso.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wagne
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

  Optional<Usuario> findByLoginAndInativo(String login, boolean inativo);
}
