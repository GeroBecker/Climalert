package com.ddsi.climalert.service;

import com.ddsi.climalert.model.RegistroClimatico;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailNotificationService {

  private final JavaMailSender mailSender;

  public EmailNotificationService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async
  public void enviarAlerta(RegistroClimatico registro) {
    try {
      String[] destinatarios = {
          "admin@clima.com",
          "emergencias@clima.com",
          "meteorologia@clima.com"
      };

      SimpleMailMessage mensaje = new SimpleMailMessage();
      mensaje.setTo(destinatarios);
      mensaje.setSubject("ALERTA: Condiciones Climáticas Críticas");
      mensaje.setText("Se han detectado condiciones climáticas inusuales:\n\n" +
          "Detalle del clima:\n" +
          "- Temperatura: " + registro.getTemperatura() + "°C\n" +
          "- Humedad: " + registro.getHumedad() + "%\n" +
          "- Fecha: " + registro.getFechaHora());

      mailSender.send(mensaje);
  } catch (Exception e) {
    // Registra el error en los logs, no dejes que el hilo muera en silencio
    log.error("Error actualizando clima de forma asíncrona: " + e.getMessage());
  }
  }
}