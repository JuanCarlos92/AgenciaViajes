package org.juancarlos.reservas.repository;

import org.juancarlos.reservas.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
ReservaEntity findReservaByNombre(String nombre);
ReservaEntity findReservaByDni(String dni);
ReservaEntity findReservaByNombreAndDni(String nombre, String dni);
}
