package io.github.wagmedrado.agendaevento.controller;

import io.github.wagmedrado.agendaevento.model.Responsavel;
import io.github.wagmedrado.agendaevento.service.ResponsavelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("Api Responsáveis")
@RequestMapping("/api/agenda/responsaveis")
public class ResponsavelController {

  @Autowired
  private ResponsavelService service;

  @GetMapping
  @ApiOperation("Obter lista de responsáveis")
  public ResponseEntity<Page<Responsavel>> obterResponsaveis(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findAll(pageable));
  }

  @GetMapping("/buscar")
  @ApiOperation("Obter lista de responsáveis com filtro de busca")
  public ResponseEntity<Page<Responsavel>> buscarResponsaveis(
          Responsavel filtro,
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
  @ApiOperation("Obter detalhes de um responsável")
  public ResponseEntity<Object> obterResponsavelPorId( @PathVariable(value = "id") Integer id ){
    Optional<Responsavel> optional = this.service.findById(id);
    if (!optional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel não encontrado.");
    }
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.service.findById(id));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation("Cadastrar um novo responsável")
  public Responsavel salvarResponsavel( @Valid @RequestBody Responsavel responsavel ){
    return this.service.save(responsavel);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("Alterar dados do responsável")
  public void atualizarResponsavel( @Valid @PathVariable Integer id, @RequestBody Responsavel responsavel ){
    this.service.findById(id).map( item -> {
      responsavel.setId(item.getId());
      this.service.save(responsavel);
      return Void.TYPE;
    }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsavel não encontrado.") );
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation("Excluir um responsável")
  public void excluirResponsavel(@PathVariable Integer id){
    this.service.findById(id).map( item -> {
      this.service.delete(item);
      return Void.TYPE;
    }).orElseThrow( () ->
    new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsavel não encontrado."));
  }
}
