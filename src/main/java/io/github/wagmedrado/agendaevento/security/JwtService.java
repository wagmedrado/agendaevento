package io.github.wagmedrado.agendaevento.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author wagne
 */
@Service
public class JwtService {

  @Value("${security.jwt.expiracao}")
  private String expiracao;

  @Value("${security.jwt.chave-assinatura}")
  private String chaveAssinatura;

  public String gerarToken(String login) {
    long expLong = Long.valueOf(expiracao);
    LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expLong);
    Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
    Date data = Date.from(instant);

    return Jwts
            .builder()
            .setSubject(login)
            .setExpiration(data)
            .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
            .compact();
  }

  private Claims obterClaims(String token)
          throws ExpiredJwtException, MalformedJwtException, UnsupportedJwtException, SignatureException, IllegalArgumentException {
    return Jwts
            .parser()
            .setSigningKey(chaveAssinatura)
            .parseClaimsJws(token)
            .getBody();
  }

  public boolean tokenValido(String token) {
    try {
      Claims claims = obterClaims(token);
      //System.out.println("clains: " + claims);
      Date dataExpiracao = claims.getExpiration();
      if (dataExpiracao == null) {
        return true;
      }
      LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      return !LocalDateTime.now().isAfter(data);
    } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException ex) {
      //System.out.println(ex.getMessage());
      return false;
    }
  }

  public String obterLoginUsuario(String token) throws ExpiredJwtException {
    return obterClaims(token).getSubject();
  }
}
