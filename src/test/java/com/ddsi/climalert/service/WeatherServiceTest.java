package com.ddsi.climalert.service;

import com.ddsi.climalert.client.WeatherApiClient;
import com.ddsi.climalert.dto.WeatherApiResponseDTO;
import com.ddsi.climalert.model.RegistroClimatico;
import com.ddsi.climalert.repository.RegistroClimaticoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

  @Mock
  private RegistroClimaticoRepository repository;

  @Mock
  private WeatherApiClient apiClient;

  @InjectMocks
  private WeatherService weatherService;

  @Test
  void cuandoLaApiRespondeCorrectamente_debeGuardarEnDb() {
    // GIVEN: Simulamos que el apiClient devuelve algo
    WeatherApiResponseDTO mockResponse = new WeatherApiResponseDTO(
        new WeatherApiResponseDTO.CurrentWeather(25.0, 60)
    );

    // Cambiamos el mock de restTemplate por el de apiClient
    when(apiClient.obtenerClimaActual()).thenReturn(mockResponse);

    // WHEN
    weatherService.procesarYGuardarClima();

    // THEN
    verify(repository, times(1)).save(any(RegistroClimatico.class));
  }

  @Test
  void cuandoLaApiFalla_noDebeGuardarNada() {
    // GIVEN: Simulamos que el apiClient devuelve algo
    WeatherApiResponseDTO mockResponse = null;

    // WHEN: Ejecutamos
    weatherService.procesarYGuardarClima();

    // THEN: Verificamos que no se intentó guardar
    verify(repository, never()).save(any(RegistroClimatico.class));
  }
}
