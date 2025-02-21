package org.juancarlos.hoteles.converter;

import org.juancarlos.hoteles.entity.HotelEntity;
import org.juancarlos.hoteles.model.dto.HotelDTO;

import java.util.ArrayList;
import java.util.List;

public class HotelConverter {
    //Convierte HotelEntity a HotelDTO
    public static HotelDTO hotelEntityToDTO(HotelEntity hotelEntity) {
        if (hotelEntity == null) return null;

        //Creamos un nuevo objeto--> HotelDTO
        return HotelDTO.builder()
                .id(hotelEntity.getId())
                .nombre(hotelEntity.getNombre())
                .categoria(hotelEntity.getCategoria())
                .precio(hotelEntity.getPrecio())
                .disponible(hotelEntity.getDisponible())
                .build();
    }

    //Metodo para recorrer la lista
    public static List<HotelDTO> hotelEntityToDTO(List<HotelEntity> hotelEntities) {
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (HotelEntity hotelEntity : hotelEntities) {
            hotelDTOs.add(hotelEntityToDTO(hotelEntity));
        }
        return hotelDTOs;
    }

}
