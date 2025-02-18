package org.juancarlos.hoteles.service;

import org.juancarlos.hoteles.converter.HotelConverter;
import org.juancarlos.hoteles.entity.HotelEntity;
import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<HotelDTO> getHotelsList() {
        List<HotelEntity> hotelEntity = hotelRepository.findAll();
        return HotelConverter.hotelEntityToDTO(hotelEntity);
    }

    @Override
    public HotelDTO getHotelId(Long id) {
        HotelEntity hotelEntity = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel con id: " +id + " no encontrado"));
        return HotelConverter.hotelEntityToDTO(hotelEntity);
    }
}
