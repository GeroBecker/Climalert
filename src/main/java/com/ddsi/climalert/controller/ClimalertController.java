package com.ddsi.climalert.controller;

import com.ddsi.climalert.model.RegistroClimatico;
import com.ddsi.climalert.repository.RegistroClimaticoRepository;
import com.ddsi.climalert.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clima")
public class ClimalertController {

  private final WeatherService weatherService;
  private final RegistroClimaticoRepository repository;

  // Inyección por constructor
  public ClimalertController(WeatherService weatherService, RegistroClimaticoRepository repository) {
    this.weatherService = weatherService;
    this.repository = repository;
  }

  // Endpoint 1: Obtener todos los registros guardados
  @GetMapping
  public List<RegistroClimatico> obtenerTodos() {
    return repository.findAll();
  }

  // Endpoint 2: Disparar el proceso de consulta a la API y guardado
  @PostMapping("/actualizar")
  public ResponseEntity<String> actualizarClima() {
    weatherService.procesarYGuardarClima();
    return ResponseEntity.ok("Clima actualizado y guardado correctamente.");
  }

}