package io.github.wagmedrado.agendaevento.service.impl;

import io.github.wagmedrado.agendaevento.exception.RegraNegocioException;
import io.github.wagmedrado.agendaevento.model.acesso.Recurso;
import io.github.wagmedrado.agendaevento.model.acesso.Sistema;
import io.github.wagmedrado.agendaevento.model.acesso.Usuario;
import io.github.wagmedrado.agendaevento.repository.UsuarioRepository;
import java.util.Arrays;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wagne
 */
@Service
public class UsuarioServiceImpl implements UserDetailsService {

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private UsuarioRepository repository;

  @Transactional
  public Usuario salvar(Usuario usuario) {
    return repository.save(usuario);
  }

  public UserDetails autenticar(Usuario usuario) {
    UserDetails user = loadUserByUsername(usuario.getLogin());
    boolean senhaOk = encoder.matches(usuario.getSenha(), user.getPassword());

    if (senhaOk) {
      return user;
    }

    throw new RegraNegocioException("Senha inválida");
  }

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = repository.findByLoginAndInativo(username, false)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não existe ou inativo."));

    //String[] roles = new String[]{"ROLE_USER"};
    String[] sistemas = usuario.getSistemas().stream().map(Sistema::getNomeRole).toArray(String[]::new);
    String[] recursos = usuario.getRecursos().stream().map(Recurso::getNomeRole).toArray(String[]::new);
    String[] roles = Stream.concat(Arrays.stream(sistemas), Arrays.stream(recursos)).toArray(String[]::new);
    System.out.println(Arrays.toString(roles));

    return User
            .builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .roles(roles)
            .build();
  }
}
