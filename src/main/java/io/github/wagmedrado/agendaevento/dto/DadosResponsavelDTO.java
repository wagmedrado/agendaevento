package io.github.wagmedrado.agendaevento.dto;

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
public class DadosResponsavelDTO {

  private Integer id;
  private String loginUsuario;
  private String nome;
  private String matricula;
  private String situacao;
}
