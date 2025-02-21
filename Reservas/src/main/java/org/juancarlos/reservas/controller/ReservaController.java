package org.juancarlos.reservas.controller;

import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.model.response.GetReservaListResponse;
import org.juancarlos.reservas.model.response.GetReservaResponse;
import org.juancarlos.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Endpoint realiza la reserva
    @GetMapping("/hoteles{nombre}/{categoria}/{precio}")
    public GetReservaListResponse reservaHotel(@PathVariable String nombre,
                                               @PathVariable String categoria,
                                               @PathVariable Double precio) {
        GetReservaListResponse response = new GetReservaListResponse();
        response.setReservasDTO(reservaService.getReservasList());
        return response;
    }

    // Endpoint reserva por id
    @GetMapping("/{id}")
    public GetReservaResponse getReservaById(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.getReservaId(id);
        GetReservaResponse response = GetReservaResponse.builder()
                .reservaDTO(reserva)
                .build();
        response.setIsOk(reserva != null);
        return response;
    }

}

