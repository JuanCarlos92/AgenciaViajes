package org.juancarlos.vuelos.controller;

import org.juancarlos.vuelos.model.dto.VueloDTO;
import org.juancarlos.vuelos.model.response.GetVueloListResponse;
import org.juancarlos.vuelos.model.response.GetVueloResponse;
import org.juancarlos.vuelos.model.response.PutVueloResponse;
import org.juancarlos.vuelos.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
    @Autowired
    private VueloService vueloService;

    //Endpoint Lista Vuelos
    @GetMapping()
    public GetVueloListResponse getVueloList() {
        GetVueloListResponse response = new GetVueloListResponse();
        response.setVuelosDTO(vueloService.getVuelosList());
        return response;
    }

    //Endpoint hotel por id
    @GetMapping("/{id}")
    public GetVueloResponse getVueloById(@PathVariable Long id) {
        VueloDTO vuelo = vueloService.getVueloId(id);
        new GetVueloResponse();
        GetVueloResponse response = GetVueloResponse.builder().vueloDTO(vuelo).build();

        response.setIsOk(true);
        return response;
    }
    @PutMapping("/{id}/reservar/{plazas}")
    public PutVueloResponse reservarPlazas(@PathVariable Long id, @PathVariable int plazas) {
        VueloDTO vueloActualizado = vueloService.reservarVuelo(id, plazas);
        PutVueloResponse response = new PutVueloResponse();
        response.setVueloDTO(vueloActualizado);
        response.setIsOk(vueloActualizado != null);

        if (vueloActualizado != null) {
            response.setMessage("Reserva realizada con éxito. Plazas actualizadas.");
        } else {
            response.setMessage("Error: No hay suficientes plazas disponibles o el vuelo no existe.");
        }

        return response;
    }
}
