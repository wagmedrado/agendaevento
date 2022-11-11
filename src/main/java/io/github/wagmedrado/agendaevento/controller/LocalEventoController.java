package io.github.wagmedrado.agendaevento.controller;

import io.github.wagmedrado.agendaevento.model.LocalEvento;
import io.github.wagmedrado.agendaevento.service.LocalEventoService;
import io.swagger.annotations.Api;
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
@Api("Api Locais de Evento")
@RequestMapping("/api/agenda/locais")
public class LocalEventoController {

  @Autowired
  private LocalEventoService service;

  @GetMapping
  public ResponseEntity<Page<LocalEvento>> obterLocaisEvento(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findAll(pageable));
  }

  @GetMapping("/buscar")
  public ResponseEntity<Page<LocalEvento>> buscarLocaisEvnto(
          LocalEvento filtro,
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
  public ResponseEntity<Object> obterLocalEventoPorId( @PathVariable(value = "id") Integer id ){
    Optional<LocalEvento> optional = this.service.findById(id);
    if (!optional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local Evento não encontrado.");
    }
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public LocalEvento salvarLocalEvento( @RequestBody @Valid LocalEvento localEvento ){
    return this.service.save(localEvento);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarLocalEvento( @PathVariable Integer id, @RequestBody @Valid LocalEvento localEvento ){
    this.service.findById(id).map( item -> {
      localEvento.setId(item.getId());
      this.service.save(localEvento);
      return Void.TYPE;
    }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local Evento não encontrado.") );
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluirLocalEvento(@PathVariable Integer id){
    this.service.findById(id).map( item -> {
      this.service.delete(item);
      return Void.TYPE;
    }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local Evento não encontrado.") );
  }
  
}
