package com.wagmedrado.agendaevento.service;

import com.wagmedrado.agendaevento.model.Equipamento;
import com.wagmedrado.agendaevento.repository.EquipamentoRepository;
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
public class EquipamentoServiceImpl implements EquipamentoService {
  
  @Autowired
  private EquipamentoRepository repository;

  @Override
  public Page<Equipamento> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  @Override
  public Page<Equipamento> findAll(Example example, Pageable pageable) {
    return this.repository.findAll(example, pageable);
  }

  @Override
  public Optional<Equipamento> findById(Integer id) {
    return this.repository.findById(id);
  }

  @Override
  @Transactional
  public Equipamento save(Equipamento equipamento) {
    return this.repository.save(equipamento);
  }

  @Override
  @Transactional
  public void delete(Equipamento equipamento) {
    this.repository.delete(equipamento);
  }
  
}
