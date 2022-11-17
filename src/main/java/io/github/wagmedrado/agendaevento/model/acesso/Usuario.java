package io.github.wagmedrado.agendaevento.model.acesso;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
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
@Entity
@Table(name = "usuario", schema = "intranet")
public class Usuario implements Serializable {

  @Id
  @Column(name = "username", length = 50)
  @NotEmpty(message = "Campo login é obrigatório.")
  private String login;

  @Column(name = "senha", length = 128)
  @NotEmpty(message = "Campo senha é obrigatório.")
  private String senha;

  @Column(name = "nome_completo", length = 100)
  @NotEmpty(message = "Campo nome é obrigatório.")
  private String nome;

  @Column(name = "inativo", nullable = false)
  @ColumnDefault(value = "false")
  private boolean inativo;

  @ManyToMany
  @JoinTable(name = "usuario_sistema", schema = "intranet",
          joinColumns = @JoinColumn(name = "username"),
          inverseJoinColumns = @JoinColumn(name = "id_sistema"))
  private Set<Sistema> sistemas = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "sistema_recurso_usuario", schema = "intranet",
          joinColumns = @JoinColumn(name = "usuario"),
          inverseJoinColumns = @JoinColumn(name = "recurso"))
  private Set<Recurso> recursos = new HashSet<>();

  public Usuario(String login, String senha) {
    this.login = login;
    this.senha = senha;
  }
}
