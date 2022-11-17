package io.github.wagmedrado.agendaevento.model.acesso;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author wagne
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sistema", schema = "intranet")
public class Sistema implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "nome", length = 100, nullable = false)
  @NotEmpty(message = "Campo nome é obrigatório.")
  private String nome;

  @Column(name = "descricao", length = 255, nullable = false)
  @NotEmpty(message = "Campo descrição é obrigatório.")
  private String descricao;

  @Column(name = "recurso", nullable = false)
  @ColumnDefault(value = "false")
  private boolean recurso;

  @Column(name = "externo", nullable = false)
  @ColumnDefault(value = "false")
  private boolean externo;

  @Column(name = "controle_login", nullable = false)
  @ColumnDefault(value = "false")
  private boolean controleLogin;

  @Column(name = "inativo", nullable = false)
  @ColumnDefault(value = "false")
  private boolean inativo;

  @Column(name = "nome_role", length = 50, nullable = false, unique = true)
  @NotEmpty(message = "Campo nome role é obrigatório.")
  private String nomeRole;
  
}
