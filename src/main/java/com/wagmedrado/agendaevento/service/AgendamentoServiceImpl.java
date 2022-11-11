package com.wagmedrado.agendaevento.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.wagmedrado.agendaevento.dto.AgendamentoDTO;
import com.wagmedrado.agendaevento.exception.RegraNegocioException;
import com.wagmedrado.agendaevento.model.Agendamento;
import com.wagmedrado.agendaevento.model.Equipamento;
import com.wagmedrado.agendaevento.model.LocalEvento;
import com.wagmedrado.agendaevento.model.StatusAgenda;
import com.wagmedrado.agendaevento.model.Usuario;
import com.wagmedrado.agendaevento.repository.AgendamentoRepository;
import com.wagmedrado.agendaevento.repository.EquipamentoRepository;
import com.wagmedrado.agendaevento.repository.LocalEventoRepository;
import com.wagmedrado.agendaevento.repository.UsuarioRepository;
import java.util.Set;

/**
 *
 * @author wagne
 */
@Service
public class AgendamentoServiceImpl implements AgendamentoService {
  
  @Autowired
  private AgendamentoRepository repository;

  @Autowired
  private LocalEventoRepository localEventoRepository;

  @Autowired
  private EquipamentoRepository equipamentoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public Page<Agendamento> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  @Override
  public Page<Agendamento> findAll(Example example, Pageable pageable) {
    return this.repository.findAll(example, pageable);
  }

  @Override
  public Optional<Agendamento> findById(Integer id) {
    return this.repository.findById(id);
  }

  @Override
  @Transactional
  public Agendamento salvar(AgendamentoDTO dto) {
//    System.out.println("salvar: " + dto.getLocal());
    Integer idLocal = dto.getLocal();
    Optional<LocalEvento> localEventoOptional = this.localEventoRepository.findById(idLocal);
    if (!localEventoOptional.isPresent()) {
      throw new RegraNegocioException("Local Agenda não encontrado.");
    }

    String loginResponsavel = dto.getResponsavel();
    Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(loginResponsavel);
    if (!usuarioOptional.isPresent()) {
      throw new RegraNegocioException("Usuário Responsável não encontrado.");
    }

    Agendamento agendamento = new Agendamento();
    agendamento.setTitulo(dto.getTitulo());
    agendamento.setDataInicio(dto.getDataInicio());
    agendamento.setDataFim(dto.getDataFim());
    agendamento.setLocal(localEventoOptional.get());
    agendamento.setResponsavel(usuarioOptional.get());
    agendamento.setObservacoes(dto.getObservacoes());
    Set<Equipamento> equipamentos = converter(dto.getEquipamentos());
    agendamento.setEquipamentos(equipamentos);

//    List<AgendamentoEquipamento> equipamentos = converter(agendamento, dto.getEquipamentos());
    this.repository.save(agendamento);
//    if (!equipamentos.isEmpty()) {
//      this.agendamentoEquipamentoRepository.saveAll(equipamentos);
//    }
//    agendamento.setEquipamentos(equipamentos);
    return agendamento;
  }

  @Override
  @Transactional
  public void delete(Agendamento item) {
    this.repository.delete(item);
  }

  @Override
  @Transactional
  public void atualizarStatus(Integer id, StatusAgenda status) {
    Optional<Agendamento> optional = this.repository.findById(id);
    if (!optional.isPresent()) {
      throw new RegraNegocioException("Agendamento não encontrado");
    }
    Agendamento agendamento = optional.get();
    agendamento.setStatus(status);
    if (status.equals(StatusAgenda.CANCELADO)) {
      agendamento.setDataCancelamento(LocalDateTime.now());
    } else if (!status.equals(StatusAgenda.PENDENTE)) {
      agendamento.setDataAprovacao(LocalDateTime.now());
    }
    this.repository.save(agendamento);
  }

  private Set<Equipamento> converter(List<Integer> items){
    if(CollectionUtils.isEmpty(items)){
      return Collections.EMPTY_SET;
    }

    return items.stream().map( id -> {
      Optional<Equipamento> optional = this.equipamentoRepository.findById(id);
      if (!optional.isPresent()) {
        return null;
      }
      return optional.get();
    }).collect(Collectors.toSet());
  }
  
}
