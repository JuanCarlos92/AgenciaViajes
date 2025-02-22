package org.juancarlos.hoteles.service;

import org.juancarlos.hoteles.converter.HotelConverter;
import org.juancarlos.hoteles.entity.HotelEntity;
import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.model.request.HotelRequest;
import org.juancarlos.hoteles.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    //Metodo Obtener Lista hoteles
    @Override
    public List<HotelDTO> getHotelsList() {
        List<HotelEntity> hotelEntity = hotelRepository.findAll();

        return HotelConverter.hotelEntityToDTO(hotelEntity);
    }

    //Metodo Obtener ID hotel
    @Override
    public HotelDTO getHotelId(Long id) {
        HotelEntity hotelEntity = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel con id: " + id + " no existe"));

        return HotelConverter.hotelEntityToDTO(hotelEntity);
    }

    //Metodo Obtener nombre hotel
    @Override
    public HotelDTO getHotelNombre(String nombre) {
        HotelEntity hotelEntity = hotelRepository.findHotelByNombre(nombre);

        return HotelConverter.hotelEntityToDTO(hotelEntity);
    }

    //Metodo guardar hotel
    @Override
    public HotelDTO postHotel(HotelRequest hotelRequest) {
        HotelEntity hotelEntity = HotelConverter.hotelDTOtoEntity(hotelRequest);
        HotelEntity guardarHotel = hotelRepository.save(hotelEntity);

        return HotelConverter.hotelEntityToDTO(guardarHotel);
    }

    //Metodo guardar hotel con una disponibilidad menos
    @Override
    public HotelDTO reservarHotel(Long id, int disponible) {
        Optional<HotelEntity> hotelEntity = hotelRepository.findById(id);

        //Si hotelEntity existe ... Lo obtiene y se almacena en vuelo (objeto de hotelEntity)
        if (hotelEntity.isPresent()) {
            HotelEntity hotel = hotelEntity.get();
            /*
            Si la disponible son >= a las que hay en hotel (hotelEntity)...
            se le resta y guarda el nuevo hotel (HotelEntity)
            */
            if (hotel.getDisponible() >= disponible) {
                hotel.setDisponible(hotel.getDisponible() - disponible);
                hotelRepository.save(hotel);

                return HotelConverter.hotelEntityToDTO(hotel);
            }
        }
        return null; // NO RESERVA EL HOTEL
    }

    //Metodo actualizar Hotel
    @Override
    public HotelDTO putHotel(HotelRequest hotelRequest) {
        HotelEntity hotelEntity = HotelConverter.hotelDTOtoEntity(hotelRequest);
        HotelEntity actualizarHotel = hotelRepository.save(hotelEntity);

        return HotelConverter.hotelEntityToDTO(actualizarHotel);
    }

    //Metodo eliminar hotel por ID
    @Override
    public void deleteHotel(Long id) {
        HotelEntity hotelEntity = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel con id: " + id + " no existe"));

        hotelRepository.delete(hotelEntity);
    }
}
