package com.ddsi.climalert.scheduler;

import com.ddsi.climalert.service.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertCheckTask {

  private final AlertService alertService;

  public AlertCheckTask(AlertService alertService) {
    this.alertService = alertService;
  }

  @Scheduled(fixedRate = 60000)
  public void ejecutarTarea() {
    alertService.verificarYAlertar();
  }
}