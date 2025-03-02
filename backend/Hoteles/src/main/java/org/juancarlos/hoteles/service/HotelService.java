package org.juancarlos.hoteles.service;

import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.model.request.HotelRequest;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getHotelsList();
    HotelDTO getHotelId(Long id);
    HotelDTO getHotelNombre(String nombre);
    HotelDTO postHotel(HotelRequest hotelRequest);
    HotelDTO reservarHotel(Long id, int disponible);
    HotelDTO putHotel(HotelRequest hotelRequest);
    void deleteHotel(Long id);

}
