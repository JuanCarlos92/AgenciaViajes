package org.juancarlos.reservas.converter;

import org.juancarlos.reservas.entity.ReservaEntity;
import org.juancarlos.reservas.model.dto.ReservaDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservaConverter {

    // Convierte ReservaEntity a ReservaDTO
    public static ReservaDTO reservaEntityToDTO(ReservaEntity reservaEntity) {
        if (reservaEntity == null) return null;

        // Creamos un nuevo objeto --> ReservaDTO
        return ReservaDTO.builder()
                .id(reservaEntity.getId())
                .nombre(reservaEntity.getNombre())
                .dni(reservaEntity.getDni())
                .idHotel(reservaEntity.getIdHotel())
                .idVuelo(reservaEntity.getIdVuelo())
                .build();
    }

    // Metodo para recorrer la lista
    public static List<ReservaDTO> reservaEntityToDTO(List<ReservaEntity> reservaEntities) {
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        for (ReservaEntity reservaEntity : reservaEntities) {
            reservaDTOs.add(reservaEntityToDTO(reservaEntity));
        }
        return reservaDTOs;
    }
}
