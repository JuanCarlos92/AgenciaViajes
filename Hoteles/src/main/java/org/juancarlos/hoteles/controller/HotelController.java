package org.juancarlos.hoteles.controller;

import lombok.AllArgsConstructor;
import org.juancarlos.hoteles.model.dto.HotelDTO;
import org.juancarlos.hoteles.model.response.GetHotelListResponse;
import org.juancarlos.hoteles.model.response.GetHotelResponse;
import org.juancarlos.hoteles.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hoteles")
@AllArgsConstructor
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //Endpoint Lista hoteles
    @GetMapping()
    public GetHotelListResponse getHotelsList() {
        GetHotelListResponse response = new GetHotelListResponse();
        response.setHotelsDTO(hotelService.getHotelsList());
        return response;
    }

    //Endpoint hotel por id
    @GetMapping("/{id}")
    public GetHotelResponse getHotelId(@PathVariable Long id) {
        HotelDTO hotel = hotelService.getHotelId(id);
        new GetHotelResponse();
        GetHotelResponse response = GetHotelResponse.builder().hotelDTO(hotel).build();

        response.setIsOk(true);
        return response;
    }
}
