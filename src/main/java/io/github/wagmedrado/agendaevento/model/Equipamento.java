package io.github.wagmedrado.agendaevento.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
@Table(name = "tb_equipamento")
public class Equipamento implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao", unique = true, length = 100)
  @NotEmpty(message = "Campo Descrição é obrigatório.")
  private String descricao;

//  @JsonIgnore
//  @ManyToMany(mappedBy = "equipamentos")
//  private Set<Agendamento> agendamentos = new HashSet<>();

}
