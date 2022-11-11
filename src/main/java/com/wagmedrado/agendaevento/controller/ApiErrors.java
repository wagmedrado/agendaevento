package com.wagmedrado.agendaevento.controller;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author w4gne
 */
public class ApiErrors {

  @Getter
  private final List<String> errors;

  public ApiErrors(List<String> errors) {
    this.errors = errors;
  }

  public ApiErrors(String mensagemErro) {
    this.errors = Arrays.asList(mensagemErro);
  }
}
