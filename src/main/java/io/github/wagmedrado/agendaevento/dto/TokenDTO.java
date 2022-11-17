package io.github.wagmedrado.agendaevento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wagne
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

  private String login;
  private String token;

}
