package com.ddsi.climalert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherApiResponseDTO(CurrentWeather current) {

  public record CurrentWeather(
      @JsonProperty("temp_c") Double tempC,
      @JsonProperty("humidity") Integer humidity
  ) {}
}