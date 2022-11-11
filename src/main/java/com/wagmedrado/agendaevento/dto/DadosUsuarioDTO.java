package com.wagmedrado.agendaevento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wagne
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosUsuarioDTO {

  private String login;
  private String nome;
}
