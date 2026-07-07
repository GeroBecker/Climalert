package com.ddsi.climalert.service;

import com.ddsi.climalert.model.RegistroClimatico;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

  private final JavaMailSender mailSender;

  public EmailNotificationService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async
  public void enviarAlerta(RegistroClimatico registro) {
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
  }
}