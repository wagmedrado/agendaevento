package com.wagmedrado.agendaevento.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Entity
@Table(name = "agendamento")
@SuppressWarnings("PersistenceUnitPresent")
public class Agendamento implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @JoinColumn(name = "local_evento_fk")
  @ManyToOne(optional = false)
  private LocalEvento local;

  @JoinColumn(name = "responsavel_fk")
  @ManyToOne(optional = false)
  private Usuario responsavel;

  @Column(name = "titulo", nullable = false, length = 100)
  private String titulo;

  @Column(name = "data_inicio", nullable = false)
  private LocalDateTime dataInicio;

  @Column(name = "data_fim", nullable = false)
  private LocalDateTime dataFim;

  @Column(name = "observacoes", columnDefinition = "TEXT")
  private String observacoes;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private StatusAgenda status = StatusAgenda.PENDENTE;

  @Column(name = "data_aprovacao")
  private LocalDateTime dataAprovacao;

  @Column(name = "data_cancelamento")
  private LocalDateTime dataCancelamento;

  @Column(name = "data_cadastro")
  private LocalDateTime dataCadastro = LocalDateTime.now();

  @OneToMany
  @JoinTable(name = "agendamento_equipamento",
          joinColumns = @JoinColumn(name = "agendamento_fk"),
          inverseJoinColumns = @JoinColumn(name = "equipamento_fk"))
  private Set<Equipamento> equipamentos = new HashSet<>();

}
