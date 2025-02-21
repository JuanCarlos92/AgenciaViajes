package org.juancarlos.hoteles.controller;

import lombok.AllArgsConstructor;
import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.model.response.*;
import org.juancarlos.hoteles.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hoteles")
@AllArgsConstructor
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //Endpoint Lista hoteles
    @GetMapping
    public GetHotelListResponse getHotelsList() {
        GetHotelListResponse response = new GetHotelListResponse();
        response.setHotelsDTO(hotelService.getHotelsList());
        return response;
    }

    //Endpoint hotel por id
    @GetMapping("/{id}")
    public GetHotelResponse getHotelId(@PathVariable Long id) {
        HotelDTO hotel = hotelService.getHotelId(id);
        GetHotelResponse response = GetHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    @GetMapping("/nombre/{nombre}")
    public GetHotelResponse getHotelNombre(@PathVariable String nombre) {
        HotelDTO hotel = hotelService.getHotelNombre(nombre);
        GetHotelResponse response = GetHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    @PostMapping
    public PostHotelResponse postHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO hotel = hotelService.postHotel(hotelDTO);
        PostHotelResponse response = PostHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    @PutMapping
    public PutHotelResponse putHotel(@RequestBody HotelDTO hotelDTO) {
        HotelDTO hotel = hotelService.putHotel(hotelDTO);
        PutHotelResponse response = PutHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }
    @PutMapping("/{id}/disponibilidad/{disponibilidad}")
    public PutHotelResponse disponibilidadHotel(@PathVariable Long id, @PathVariable int disponibilidad) {
        HotelDTO hotelActualizado = hotelService.diponibilidadHotel(id, disponibilidad);
        PutHotelResponse response = new PutHotelResponse();
        response.setHotelDTO(hotelActualizado);
        response.setIsOk(hotelActualizado != null);

        if (hotelActualizado != null) {
            response.setMessage("Disponibilidad del hotel actualizada");
        }else{
            response.setMessage("Error: No está disponible el hotel");
        }
        return response;
    }

    @DeleteMapping
    public DeleteHotelResponse eliminarHotel(@PathVariable Long id) {
        HotelDTO hotel = hotelService.deleteHotel(id);
        DeleteHotelResponse response = DeleteHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }
}
