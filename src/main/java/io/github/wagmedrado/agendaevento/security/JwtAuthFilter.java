package io.github.wagmedrado.agendaevento.security;

import io.github.wagmedrado.agendaevento.service.impl.UsuarioServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author wagne
 */
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired
  private JwtService jwtService;

  @Autowired
  private UsuarioServiceImpl usuarioService;

  @Override
  protected void doFilterInternal(
          HttpServletRequest httpServletRequest,
          HttpServletResponse httpServletResponse,
          FilterChain filterChain) throws ServletException, IOException {

    String authorization = httpServletRequest.getHeader("Authorization");

    if (authorization != null && authorization.startsWith("Bearer")) {
      String token = authorization.split(" ")[1];
      boolean isValid = jwtService.tokenValido(token);

      if (isValid) {
        String loginUsuario = jwtService.obterLoginUsuario(token);
        UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(user);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
