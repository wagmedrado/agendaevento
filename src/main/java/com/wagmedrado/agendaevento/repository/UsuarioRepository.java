package com.wagmedrado.agendaevento.repository;


import com.wagmedrado.agendaevento.model.Usuario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author wagne
 */
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

  @Query("SELECT u FROM Usuario u WHERE u.inativo = FALSE")
  public Page<Usuario> findAllAtivos(Pageable pageable);

  public Page<Usuario> findByNomeStartsWithIgnoreCase(String nome, Pageable pageable);

  public Optional<Usuario> findByLogin(String login);

  public Optional<Usuario> findByLoginAndNome(String login, String nome);
}
