package com.ddsi.climalert.service;

import com.ddsi.climalert.model.RegistroClimatico;
import com.ddsi.climalert.repository.RegistroClimaticoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AlertService {

  private final RegistroClimaticoRepository repository;
  private final EmailNotificationService emailNotificationService;

  public AlertService(RegistroClimaticoRepository repository, EmailNotificationService emailNotificationService) {
    this.repository = repository;
    this.emailNotificationService = emailNotificationService;
  }

  @Transactional
  public void verificarYAlertar() {
    // Busca (en caso de que haya) el registro sin procesar mas reciente
    Optional<RegistroClimatico> registroOpt = repository.findFirstByProcesadoFalseOrderByFechaHoraAsc();

    if (registroOpt.isPresent()) {
      RegistroClimatico registro = registroOpt.get();

      // Si el registro que encuentra es muy antiguo no tiene sentido utlizarlo
      // Considero antiguo más de 10 minutos
      boolean esMuyAntiguo = Duration.between(registro.getFechaHora(), LocalDateTime.now()).toMinutes() > 10;

      if (!esMuyAntiguo) {
        if (registro.getTemperatura() > 35 && registro.getHumedad() > 60) {
          emailNotificationService.enviarAlerta(registro);
        }
      }
      // Marcamos como procesado en cualquier caso (para descartar el dato obsoleto)
      registro.setProcesado(true);
    }
  }
}