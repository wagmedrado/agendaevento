package io.github.wagmedrado.agendaevento.service;

import io.github.wagmedrado.agendaevento.model.LocalEvento;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author wagne
 */
public interface LocalEventoService {

  public Page<LocalEvento> findAll(Pageable pageable);
  public Page<LocalEvento> findAll(Example example, Pageable pageable);
  public Optional<LocalEvento> findById(Integer id);

  public LocalEvento save(LocalEvento localEvento);
  public void delete(LocalEvento localEvento);
}
