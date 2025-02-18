package org.juancarlos.hoteles.controller;

import org.juancarlos.hoteles.model.Hotel;
import org.juancarlos.hoteles.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping()
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getHotels();

        //HttpHeaders = añadir encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.add("Hoteles", String.valueOf(hotels.size()));
        System.out.println(headers.get("Hoteles"));

        return new ResponseEntity<>(hotels, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotel(id);

        //HttpHeaders = añadir encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.add("Hotel-ID", String.valueOf(id));

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
}
