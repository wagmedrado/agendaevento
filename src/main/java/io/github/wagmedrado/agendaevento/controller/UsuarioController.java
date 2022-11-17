package io.github.wagmedrado.agendaevento.controller;

import io.github.wagmedrado.agendaevento.dto.CredencialDTO;
import io.github.wagmedrado.agendaevento.dto.TokenDTO;
import io.github.wagmedrado.agendaevento.exception.RegraNegocioException;
import io.github.wagmedrado.agendaevento.model.acesso.Usuario;
import io.github.wagmedrado.agendaevento.security.JwtService;
import io.github.wagmedrado.agendaevento.service.impl.UsuarioServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author wagne
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioServiceImpl service;

  @Autowired
  private JwtService jwtService;
  
  @Autowired
  PasswordEncoder passwordEncoder;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Usuario salvar(@RequestBody @Valid Usuario usuario) {
    String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
    usuario.setSenha(senhaCriptografada);
    return service.salvar(usuario);
  }

  @PostMapping("/auth")
  public TokenDTO autenticar(@RequestBody CredencialDTO credencial) {
    try {
      Usuario usuario = new Usuario(credencial.getLogin(), credencial.getSenha());
      service.autenticar(usuario);
      String token = jwtService.gerarToken(usuario.getLogin());
      return new TokenDTO(usuario.getLogin(), token);
    } catch (UsernameNotFoundException | RegraNegocioException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
  }
}
