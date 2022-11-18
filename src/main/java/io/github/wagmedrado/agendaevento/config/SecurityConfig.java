package io.github.wagmedrado.agendaevento.config;

import io.github.wagmedrado.agendaevento.security.JwtAuthFilter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author wagne
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
          .csrf().disable()
          .authorizeHttpRequests()
          .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
          .anyRequest().authenticated();
    http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getOutputStream().println(ex.getMessage());
      //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    });
    http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().antMatchers(
            "/",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html","/webjars/**");
  }

  @Bean
  public OncePerRequestFilter jwtFilter() {
    return new JwtAuthFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
