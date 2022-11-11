package com.wagmedrado.agendaevento.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author wagne
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
  @Id
  @Column(name="username", length = 50)
  private String login;

  @Column(name="senha", length = 128)
  private String senha;

  @Column(name="nome_completo", length = 100)
  private String nome;

  @Column(name="matricula_rm", length = 10, nullable = true)
  private String matriculaRm;

  @Column(name="usuario_ad")
  private boolean usuarioAd;

  @Column(name="inativo")
  private boolean inativo;

  @Column(name="id_telegram", nullable = true)
  private Integer idTelegram;

  @Column(name="matricula")
  private Integer matricula;
}
