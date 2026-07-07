package com.ddsi.climalert.service;

import com.ddsi.climalert.client.WeatherApiClient;
import com.ddsi.climalert.dto.WeatherApiResponseDTO;
import com.ddsi.climalert.model.RegistroClimatico;
import com.ddsi.climalert.repository.RegistroClimaticoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
public class WeatherService {

  private final WeatherApiClient apiClient;
  @Autowired
  private final RegistroClimaticoRepository repository;


  public WeatherService(WeatherApiClient apiClient, RegistroClimaticoRepository repository) {
    this.apiClient = apiClient;
    this.repository = repository;
  }

  @Async
  public void procesarYGuardarClima() {
    try {
      // 1. Obtener datos del cliente
      WeatherApiResponseDTO dto = apiClient.obtenerClimaActual();

      // 2. Verificar que recibimos datos y persistir en base de datos
      if (dto != null) {
        RegistroClimatico registro = new RegistroClimatico();

        registro.setTemperatura(dto.current().tempC());
        registro.setHumedad(dto.current().humidity());
        registro.setFechaHora(LocalDateTime.now());
        registro.setProcesado(false);
        // Imprimo el registro que se guarda para testeo
        RegistroClimatico registroGuardado = repository.save(registro);
        System.out.println(registroGuardado);
      }
    } catch (Exception e) {
      // Registra el error en los logs, no dejes que el hilo muera en silencio
      log.error("Error actualizando clima de forma asíncrona: " + e.getMessage());
    }
  }

}