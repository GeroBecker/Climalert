package com.ddsi.climalert.scheduler;

import com.ddsi.climalert.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherFetchTask {

  private final WeatherService weatherService;

  public WeatherFetchTask(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @Scheduled(fixedRate = 300000)
  public void ejecutarTarea() {
    weatherService.procesarYGuardarClima();
  }
}