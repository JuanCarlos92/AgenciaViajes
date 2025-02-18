package org.juancarlos.vuelos.repository;

import org.juancarlos.vuelos.entity.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<VueloEntity, Long> {
}
