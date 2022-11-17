package io.github.wagmedrado.agendaevento.service;

import io.github.wagmedrado.agendaevento.model.acesso.Usuario;
import java.util.Optional;

/**
 *
 * @author wagne
 */
public interface UsuarioService {

  Optional<Usuario> findByLoginAndInativo(String login, boolean inativo);
}
