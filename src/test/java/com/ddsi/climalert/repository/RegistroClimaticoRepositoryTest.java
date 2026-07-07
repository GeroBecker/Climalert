package com.ddsi.climalert.repository;

import com.ddsi.climalert.model.RegistroClimatico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RegistroClimaticoRepositoryTest {

  @Autowired
  private RegistroClimaticoRepository repository;

  @Test
  void debeGuardarYRecuperarRegistro() {
    // GIVEN: Un objeto preparado
    RegistroClimatico registro = new RegistroClimatico( 25.0, 60, LocalDateTime.now(), false);

    // WHEN: Guardamos
    repository.save(registro);

    // THEN: Verificamos que existe
    List<RegistroClimatico> lista = repository.findAll();
    assertThat(lista).hasSize(1);
    assertThat(lista.get(0).getTemperatura()).isEqualTo(25.0);
  }
}