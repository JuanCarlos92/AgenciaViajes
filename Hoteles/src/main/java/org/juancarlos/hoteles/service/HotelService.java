package org.juancarlos.hoteles.service;

import org.juancarlos.hoteles.model.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getHotels();
    Hotel getHotel(Long id);
}
