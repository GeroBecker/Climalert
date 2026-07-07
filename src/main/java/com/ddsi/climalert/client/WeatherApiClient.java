package com.ddsi.climalert.client;

import com.ddsi.climalert.dto.WeatherApiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClient {

  private final RestTemplate restTemplate;

  public WeatherApiClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  // Inyectamos los valores desde el application.properties
  @Value("${weather.api.url}")
  private String apiUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.location}")
  private String location;

  public WeatherApiResponseDTO obtenerClimaActual() {
    // Construimos la URL exacta con la API Key y la ubicación fija (CABA)
    String url = String.format("%s?key=%s&q=%s", apiUrl, apiKey, location);

    // Hacemos la petición GET y Spring automáticamente convierte el JSON en nuestro Record DTO
    return restTemplate.getForObject(url, WeatherApiResponseDTO.class);
  }
}