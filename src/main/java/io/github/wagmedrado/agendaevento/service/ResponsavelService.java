package io.github.wagmedrado.agendaevento.service;

import io.github.wagmedrado.agendaevento.model.Responsavel;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author wagne
 */
public interface ResponsavelService {

  public Page<Responsavel> findAll(Pageable pageable);
  public Page<Responsavel> findAll(Example example, Pageable pageable);
  public Optional<Responsavel> findById(Integer id);

  public Responsavel save(Responsavel responsavel);
  public void delete(Responsavel responsavel);
}
