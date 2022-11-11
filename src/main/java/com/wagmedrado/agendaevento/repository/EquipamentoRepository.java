package com.wagmedrado.agendaevento.repository;

import com.wagmedrado.agendaevento.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wagne
 */
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {
  
}
