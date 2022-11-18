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
import org.springframework.util.ObjectUtils;
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
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {

    if (!hasAuthorizationBearer(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    String authorization = request.getHeader("Authorization");
    String token = authorization.split(" ")[1];
    boolean isValid = jwtService.tokenValido(token);

    if (isValid) {
      String loginUsuario = jwtService.obterLoginUsuario(token);
      UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
      System.out.println("\nusuario: " + usuario.getUsername());
      System.out.println("acessos: " + usuario.getAuthorities() + "\n");

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);

      filterChain.doFilter(request, response);
      return;
    }

    filterChain.doFilter(request, response);
  }

  private boolean hasAuthorizationBearer(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    return !(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer"));
  }
}
