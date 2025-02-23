package org.juancarlos.hoteles.controller;

import lombok.AllArgsConstructor;
import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.model.request.HotelRequest;
import org.juancarlos.hoteles.model.response.*;
import org.juancarlos.hoteles.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hoteles")
@AllArgsConstructor
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //Endpoint Obtener Lista HOTELES
    @GetMapping
    public GetHotelListResponse getHotelsList() {
        GetHotelListResponse response = new GetHotelListResponse();
        response.setHotelsDTO(hotelService.getHotelsList());

        return response;
    }

    //Endpoint Obtener HOTEL por ID
    @GetMapping("/{id}")
    public GetHotelResponse getHotelId(@PathVariable Long id) {
        HotelDTO hotel = hotelService.getHotelId(id);
        GetHotelResponse response = GetHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Obtener HOTEL por nombre
    @GetMapping("/nombre/{nombre}")
    public GetHotelResponse getHotelNombre(@PathVariable String nombre) {
        HotelDTO hotel = hotelService.getHotelNombre(nombre);
        GetHotelResponse response = GetHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Crear HOTEL
    @PostMapping
    public PostHotelResponse postHotel(@RequestBody HotelRequest hotelRequest) {
        HotelDTO hotel = hotelService.postHotel(hotelRequest);
        PostHotelResponse response = PostHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Actualizar reserva HOTEL
    @PostMapping("/{id}/{disponible}")
    public PostHotelResponse reservarHotel(@PathVariable Long id, @PathVariable int disponible) {
        HotelDTO hotelActualizado = hotelService.reservarHotel(id, disponible);
        PostHotelResponse response = new PostHotelResponse();
        response.setHotelDTO(hotelActualizado);
        response.setIsOk(hotelActualizado != null);

        if (hotelActualizado != null) {
            response.setMessage("La Disponibilidad del hotel con id " + id + " ha sido actualizado");
        } else {
            response.setMessage("Error: El hotel que has seleccionado no está disponible");
        }

        response.setIsOk(true);
        return response;
    }

    //Endpoint Actualizar HOTEL
    @PutMapping
    public PutHotelResponse putHotel(@RequestBody HotelRequest hotelRequest) {
        HotelDTO hotel = hotelService.putHotel(hotelRequest);
        PutHotelResponse response = PutHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Eliminar Hotel por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);

        return ResponseEntity.ok("Hotel eliminado correctamente");
    }
}
