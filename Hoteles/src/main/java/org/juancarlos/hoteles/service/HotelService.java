package org.juancarlos.hoteles.service;

import org.juancarlos.hoteles.model.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getHotelsList();
    HotelDTO getHotelId(Long id);
}
