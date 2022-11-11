package com.wagmedrado.agendaevento.service;

import com.wagmedrado.agendaevento.model.Equipamento;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author wagne
 */
public interface EquipamentoService {

  public Page<Equipamento> findAll(Pageable pageable);
  public Page<Equipamento> findAll(Example example, Pageable pageable);
  public Optional<Equipamento> findById(Integer id);

  public Equipamento save(Equipamento equipamento);
  public void delete(Equipamento equipamento);
}
