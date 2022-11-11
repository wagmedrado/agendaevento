package io.github.wagmedrado.agendaevento.service.impl;

import io.github.wagmedrado.agendaevento.model.LocalEvento;
import io.github.wagmedrado.agendaevento.repository.LocalEventoRepository;
import io.github.wagmedrado.agendaevento.service.LocalEventoService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wagne
 */
@Service
public class LocalEventoServiceImpl implements LocalEventoService {
  
  @Autowired
  private LocalEventoRepository repository;

  @Override
  public Page<LocalEvento> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  @Override
  public Page<LocalEvento> findAll(Example example, Pageable pageable) {
    return this.repository.findAll(example, pageable);
  }

  @Override
  public Optional<LocalEvento> findById(Integer id) {
    return this.repository.findById(id);
  }

  @Override
  @Transactional
  public LocalEvento save(LocalEvento localEvento) {
    return this.repository.save(localEvento);
  }

  @Override
  @Transactional
  public void delete(LocalEvento localEvento) {
    this.repository.delete(localEvento);
  }
}
