package io.github.wagmedrado.agendaevento.model.acesso;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wagne
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recurso", schema = "intranet")
public class Recurso implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "descricao", length = 100, nullable = false)
  @NotEmpty(message = "Campo descrição é obrigatório.")
  private String descricao;

  @Column(name = "nome_role", length = 50, nullable = false, unique = true)
  @NotEmpty(message = "Campo nome role é obrigatório.")
  private String nomeRole;

  @Column(name = "menu", length = 50)
  private String menu;

  @Column(name = "pagina", length = 50)
  private String pagina;

  @ManyToOne
  @JoinColumn(name = "sistema", nullable = false)
  private Sistema sistema;
}
