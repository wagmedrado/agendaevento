package io.github.wagmedrado.agendaevento.service.impl;

import io.github.wagmedrado.agendaevento.model.Responsavel;
import io.github.wagmedrado.agendaevento.repository.ResponsavelRepository;
import io.github.wagmedrado.agendaevento.service.ResponsavelService;
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
public class ResponsavelServiceImpl implements ResponsavelService {

  @Autowired
  private ResponsavelRepository repository;

  @Override
  public Page<Responsavel> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  @Override
  public Page<Responsavel> findAll(Example example, Pageable pageable) {
    return this.repository.findAll(example, pageable);
  }

  @Override
  public Optional<Responsavel> findById(Integer id) {
    return this.repository.findById(id);
  }

  @Override
  @Transactional
  public Responsavel save(Responsavel responsavel) {
    return this.repository.save(responsavel);
  }

  @Override
  @Transactional
  public void delete(Responsavel responsavel) {
    this.repository.delete(responsavel);
  }
  
}
