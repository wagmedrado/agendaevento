package com.wagmedrado.agendaevento.dto;

import com.wagmedrado.agendaevento.model.LocalEvento;
import java.time.LocalDateTime;
import java.util.Set;
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
public class DadosAgendamentoDTO {

  private Integer id;
  private LocalEvento local;
  private String titulo;
  private LocalDateTime dataInicio;
  private LocalDateTime dataFim;
  private DadosUsuarioDTO responsavel;
  private String observacoes;
  private String status;
  private LocalDateTime dataAprovacao;
  private LocalDateTime dataCancelamento;
  private LocalDateTime dataCadastro;
  private Set<DadosEquipamentoDTO> equipamentos;
}
