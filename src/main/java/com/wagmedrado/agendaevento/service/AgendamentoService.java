package com.wagmedrado.agendaevento.service;

import com.wagmedrado.agendaevento.dto.AgendamentoDTO;
import com.wagmedrado.agendaevento.model.Agendamento;
import com.wagmedrado.agendaevento.model.StatusAgenda;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author wagne
 */
public interface AgendamentoService {

  public Page<Agendamento> findAll(Pageable pageable);
  public Page<Agendamento> findAll(Example example, Pageable pageable);
  public Optional<Agendamento> findById(Integer id);

  public Agendamento salvar(AgendamentoDTO dto);
  public void atualizarStatus(Integer id, StatusAgenda status);
  public void delete(Agendamento item);
}
