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
public class DadosEquipamentoDTO {

  private Integer id;
  private String descricao;
}
