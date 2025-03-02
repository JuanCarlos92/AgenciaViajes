package org.juancarlos.hoteles.repository;

import org.juancarlos.hoteles.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    HotelEntity findHotelByNombre(String nombre);
}
