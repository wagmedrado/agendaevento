package com.wagmedrado.agendaevento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ws3Application {

  public static void main(String[] args) {
//    System.out.println(LocalDateTime.now());
    SpringApplication.run(Ws3Application.class, args);
  }

  @RequestMapping("/")
  public ResponseEntity index() {
    return ResponseEntity.ok("ws3 api wagne");
  }

}
