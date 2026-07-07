package com.ddsi.climalert.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


// Tira error porque no encuentra la tabla ya que Entity la crea en runtime y el IDE no lo sabe
@Entity
@Data
@NoArgsConstructor
@Table(name = "registros_climaticos")
public class RegistroClimatico {


  // Opte por SEQUENCE en vez de ID.
  // Permite que Hibernate obtenga bloques de IDs de una y luego haga las inserciones en batches
  // Tira error porque no encuentra la sequence ya que Entity la crea en runtime y el IDE no lo sabe
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registro_seq")
  @SequenceGenerator(name = "registro_seq", sequenceName = "registro_climatico_seq", allocationSize = 50)
  private Long id;
  private Double temperatura;
  private Integer humedad;
  private LocalDateTime fechaHora;
  private Boolean procesado;


  public RegistroClimatico(Double temperatura, Integer humedad, LocalDateTime fechaHora, Boolean procesado) {
    this.temperatura = temperatura;
    this.humedad = humedad;
    this.fechaHora = fechaHora;
    this.procesado = procesado;
  }
}