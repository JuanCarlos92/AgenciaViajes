package org.juancarlos.hoteles.converter;

import org.juancarlos.hoteles.entity.HotelEntity;
import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.model.request.HotelRequest;

import java.util.ArrayList;
import java.util.List;

public class HotelConverter {
    //Convierte HotelEntity a HotelDTO
    public static HotelDTO hotelEntityToDTO(HotelEntity hotelEntity) {
        if (hotelEntity == null) return null;

        //Creamos un nuevo objeto --> HotelDTO
        return HotelDTO.builder()
                .id(hotelEntity.getId())
                .nombre(hotelEntity.getNombre())
                .categoria(hotelEntity.getCategoria())
                .precio(hotelEntity.getPrecio())
                .disponible(hotelEntity.getDisponible())
                .build();
    }
    //Convierte HotelDTO a HotelEntity
    public static HotelEntity hotelDTOtoEntity(HotelRequest hotelDTO) {
        if (hotelDTO == null) return null;

        //Creamos un nuevo objeto --> HotelEntity
        return HotelEntity.builder()
                .id(hotelDTO.getId())
                .nombre(hotelDTO.getNombre())
                .categoria(hotelDTO.getCategoria())
                .precio(hotelDTO.getPrecio())
                .disponible(hotelDTO.getDisponible())
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
