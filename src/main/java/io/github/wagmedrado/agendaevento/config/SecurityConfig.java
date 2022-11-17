package io.github.wagmedrado.agendaevento.config;

import io.github.wagmedrado.agendaevento.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

//  @Autowired
//  private UsuarioServiceImpl usuarioService;
//
//  @Autowired
//  private JwtService jwtService;
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//            .userDetailsService(usuarioService)
//            .passwordEncoder(passwordEncoder());
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/clientes/**")
//            .hasAnyRole("USER", "ADMIN")
//            .antMatchers("/api/pedidos/**")
//            .hasAnyRole("USER", "ADMIN")
//            .antMatchers("/api/produtos/**")
//            .hasRole("ADMIN")
//            .antMatchers(HttpMethod.POST, "/api/usuarios/**")
//            .permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests()
          .antMatchers(HttpMethod.POST, "/api/usuarios/**")
            .permitAll()
//          .antMatchers("/v2/api-docs")
//            .permitAll()
          .anyRequest().authenticated()
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
          .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
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
