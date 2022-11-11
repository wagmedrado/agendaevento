package io.github.wagmedrado.agendaevento.dto;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wagne
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
  @NotNull(message = "Informe o ID do local da agenda.")
  private Integer local;

  @NotNull(message = "Iforme o ID do responsável.")
  private Integer responsavel;

  @NotEmpty(message = "Iforme o título.")
  private String titulo;

  @NotNull(message = "Informe a data início.")
  private LocalDateTime dataInicio;

  @NotNull(message = "Informe a data fim.")
  private LocalDateTime dataFim;

  private String observacoes;

  private String status = "PENDENTE";

  private LocalDateTime dataAprovacao;

  private LocalDateTime dataCancelamento;

  private List<Integer> equipamentos;
}
