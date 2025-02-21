package org.juancarlos.reservas.controller;

import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.model.request.ReservaRequest;
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
    @PostMapping("/hoteles")
    public GetReservaResponse reservarHotel(@RequestBody ReservaRequest reservaRequest) {
        GetReservaResponse response = new GetReservaResponse();

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

