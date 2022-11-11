package io.github.wagmedrado.agendaevento.model;

import io.github.wagmedrado.agendaevento.enums.SituacaoResponsavel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author wagne
 */
@Data
@Entity
@Table(name = "tb_responsavel")
public class Responsavel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name="nome", length = 100, nullable = false)
  @NotEmpty(message = "Campo Nome é obrigatório.")
  private String nome;

  @Column(name="matricula", length = 10, nullable = false, unique = true)
  @NotEmpty(message = "Campo Matrícula é obrigatório.")
  private String matricula;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 50)
  private SituacaoResponsavel situacao = SituacaoResponsavel.ATIVO;

  @Column(name="usuario_fk", length = 50)
  private String loginUsuario;

}
