package com.wagmedrado.agendaevento.repository;

import com.wagmedrado.agendaevento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wagne
 */
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
  
}
