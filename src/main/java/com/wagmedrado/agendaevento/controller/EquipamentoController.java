package com.wagmedrado.agendaevento.controller;

import com.wagmedrado.agendaevento.model.Equipamento;
import com.wagmedrado.agendaevento.service.EquipamentoService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/api/agenda/equipamentos")
public class EquipamentoController {

  @Autowired
  private EquipamentoService service;

  @GetMapping
  public ResponseEntity<Page<Equipamento>> obterEquipamentos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findAll(pageable));
  }

  @GetMapping("/buscar")
  public ResponseEntity<Page<Equipamento>> buscarEquipamentos(
          Equipamento filtro,
          @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
  ) {
    ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example example = Example.of(filtro, matcher);
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findAll(example, pageable));
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> obterEquipamentoPorId( @PathVariable(value = "id") Integer id ){
    Optional<Equipamento> optional = this.service.findById(id);
    if (!optional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento não encontrado.");
    }
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Equipamento salvarEquipamento( @RequestBody @Valid Equipamento equipamento ){
    return this.service.save(equipamento);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarEquipamento( @PathVariable Integer id, @RequestBody @Valid Equipamento equipamento ){
    this.service.findById(id).map( item -> {
      equipamento.setId(item.getId());
      this.service.save(equipamento);
      return Void.TYPE;
    }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado.") );
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluirEquipamento(@PathVariable Integer id){
    this.service.findById(id).map( item -> {
      this.service.delete(item);
      return Void.TYPE;
    }).orElseThrow( () ->
    new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipamento não encontrado."));
  }
}
