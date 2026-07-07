package com.ddsi.climalert.controller;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

  // Para manejo de errores, a mejorar
  //TODO
  @RequestMapping("/error")
  public String handleError() {
    return "Ups, algo salió mal. Inténtalo más tarde.";
  }
}