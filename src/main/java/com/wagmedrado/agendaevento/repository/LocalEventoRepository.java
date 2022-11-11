package com.wagmedrado.agendaevento.repository;

import com.wagmedrado.agendaevento.model.LocalEvento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wagne
 */
public interface LocalEventoRepository extends JpaRepository<LocalEvento, Integer> {
  
}
