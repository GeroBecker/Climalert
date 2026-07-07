package com.ddsi.climalert.repository;

import com.ddsi.climalert.model.RegistroClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroClimaticoRepository extends JpaRepository<RegistroClimatico, Long> {
  // Al extender de JpaRepository, ya heredamos métodos como: save(), findAll(), findById(), etc.
  // No hace falta que crear una clase que implemente la interfaz porque la clase se genera de forma dinamica (Proxy) por Jpa.

  // Busca (en caso de que haya) el registro sin procesar mas reciente
  Optional<RegistroClimatico> findFirstByProcesadoFalseOrderByFechaHoraAsc();
}