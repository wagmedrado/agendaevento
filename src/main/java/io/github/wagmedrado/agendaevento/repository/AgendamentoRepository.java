package io.github.wagmedrado.agendaevento.repository;

import io.github.wagmedrado.agendaevento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author wagne
 */
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

}
