package com.wagmedrado.agendaevento.controller;

import com.wagmedrado.agendaevento.dto.AgendamentoDTO;
import com.wagmedrado.agendaevento.dto.DadosAgendamentoDTO;
import com.wagmedrado.agendaevento.dto.DadosEquipamentoDTO;
import com.wagmedrado.agendaevento.dto.DadosUsuarioDTO;
import com.wagmedrado.agendaevento.model.Agendamento;
import com.wagmedrado.agendaevento.model.Equipamento;
import com.wagmedrado.agendaevento.model.StatusAgenda;
import com.wagmedrado.agendaevento.model.Usuario;
import com.wagmedrado.agendaevento.service.AgendamentoService;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author wagne
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/agenda/agendamentos")
@Transactional
public class AgendamentoController {

  @Autowired
  private AgendamentoService service;

  @GetMapping
  public ResponseEntity<Page<DadosAgendamentoDTO>> obterAgendamentos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findAll(pageable).map(item -> converter(item)));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public DadosAgendamentoDTO salvarAgendamento( @Valid @RequestBody AgendamentoDTO dto ){
    Agendamento result = this.service.salvar(dto);
    return converter(result);
  }

  @GetMapping("{id}")
  public ResponseEntity<DadosAgendamentoDTO> obterAgendamentoPorId( @PathVariable Integer id ){
    Optional<Agendamento> optional = this.service.findById(id);
    if (!optional.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado.");
    }
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(converter(optional.get()));
  }

  @PatchMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateStatus( @PathVariable Integer id, @RequestBody String novoStatus ){
    this.service.atualizarStatus(id, StatusAgenda.valueOf(novoStatus));
  }

  private DadosAgendamentoDTO converter(Agendamento item) {
    return DadosAgendamentoDTO
            .builder()
            .id(item.getId())
            .titulo(item.getTitulo())
            .status(item.getStatus().name())
            .observacoes(item.getObservacoes())
            .dataInicio(item.getDataInicio())
            .dataFim(item.getDataFim())
            .dataCadastro(item.getDataCadastro())
            .local(item.getLocal())
            .responsavel(converter(item.getResponsavel()))
            .equipamentos(converter(item.getEquipamentos()))
            .build();
  }

  private DadosUsuarioDTO converter(Usuario item) {
    return DadosUsuarioDTO.builder().login(item.getLogin()).nome(item.getNome()).build();
  }

  private Set<DadosEquipamentoDTO> converter(Set<Equipamento> items) {
    if(CollectionUtils.isEmpty(items)){
      return Collections.EMPTY_SET;
    }
    return items
            .stream()
            .map(item -> DadosEquipamentoDTO.builder().id(item.getId()).descricao(item.getDescricao()).build())
            .collect(Collectors.toSet());
  }
}
