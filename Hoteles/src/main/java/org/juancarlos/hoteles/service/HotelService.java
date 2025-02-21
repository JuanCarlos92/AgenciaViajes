package org.juancarlos.hoteles.service;

import org.juancarlos.hoteles.model.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getHotelsList();
    HotelDTO getHotelId(Long id);
    HotelDTO getHotelNombre(String nombre);
    HotelDTO postHotel(HotelDTO hotelDTO);
    HotelDTO putHotel(HotelDTO hotelDTO);
    HotelDTO diponibilidadHotel(Long id, int diponibilidad);
    HotelDTO deleteHotel(Long id);

}
