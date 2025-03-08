package org.juancarlos.reservas.controller;

import org.juancarlos.reservas.model.dto.ReservaDTO;
import org.juancarlos.reservas.model.request.ReservaRequest;
import org.juancarlos.reservas.model.response.GetReservaListResponse;
import org.juancarlos.reservas.model.response.GetReservaResponse;
import org.juancarlos.reservas.model.response.PostReservaResponse;
import org.juancarlos.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Endpoint realiza la reserva
    @PostMapping
    public PostReservaResponse realizarReserva(@RequestBody ReservaRequest reservaRequest) throws Exception {
        ReservaDTO reserva = reservaService.reservar(reservaRequest);
        PostReservaResponse response = PostReservaResponse.builder().reservaDTO(reserva).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Obtener Lista RESERVAS
    @GetMapping
    public GetReservaListResponse getReservaList(){
        GetReservaListResponse response = new GetReservaListResponse();
        response.setReservasDTO(reservaService.getReservasList());

        return response;
    }

    //Endpoint Obtener RESERVA por ID
    @GetMapping("/{id}")
    public GetReservaResponse getReserva(@PathVariable Long id){
        ReservaDTO reserva = reservaService.getReservaId(id);
        GetReservaResponse response = GetReservaResponse.builder().reservaDTO(reserva).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Obtener RESERVA por Nombre
    @GetMapping("/nombre/{nombre}")
    public GetReservaResponse getReservaNombre(@PathVariable String nombre){
        ReservaDTO reserva = reservaService.getReservaNombre(nombre);
        GetReservaResponse response = GetReservaResponse.builder().reservaDTO(reserva).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Obtener RESERVA por dni
    @GetMapping("/dni/{dni}")
    public GetReservaResponse getReservaDni(@PathVariable String dni){
        ReservaDTO reserva = reservaService.getReservaDNI(dni);
        GetReservaResponse response = GetReservaResponse.builder().reservaDTO(reserva).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Obtener RESERVA por nombre y dni
    @GetMapping("/nombreydni/{nombre}/{dni}")
    public GetReservaResponse getReservaNombreYDni(@PathVariable String nombre, @PathVariable String dni){
        ReservaDTO reserva = reservaService.getReservaNombreYDni(nombre, dni);
        GetReservaResponse response = GetReservaResponse.builder().reservaDTO(reserva).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id){
        reservaService.deleteReserva(id);

        return ResponseEntity.ok("Reserva eliminada");
    }
}

