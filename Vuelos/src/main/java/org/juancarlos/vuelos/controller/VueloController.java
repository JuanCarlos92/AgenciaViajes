package org.juancarlos.vuelos.controller;

import org.juancarlos.vuelos.model.dto.VueloDTO;
import org.juancarlos.vuelos.model.request.VueloRequest;
import org.juancarlos.vuelos.model.response.*;
import org.juancarlos.vuelos.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
    @Autowired
    private VueloService vueloService;

    //Endpoint Lista VUELOS
    @GetMapping()
    public GetVueloListResponse getVueloList() {
        GetVueloListResponse response = new GetVueloListResponse();
        response.setVuelosDTO(vueloService.getVuelosList());

        return response;
    }

    //Endpoint Obtener VUELO por ID
    @GetMapping("/{id}")
    public GetVueloResponse getVueloById(@PathVariable Long id) {
        VueloDTO vuelo = vueloService.getVueloId(id);
        GetVueloResponse response = GetVueloResponse.builder().vueloDTO(vuelo).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Obtener VUELO por compañia
    @GetMapping("/company/{company}")
    public GetVueloResponse getVueloByNombre(@PathVariable String company) {
        VueloDTO vuelo = vueloService.getVueloCompany(company);
        GetVueloResponse response = GetVueloResponse.builder().vueloDTO(vuelo).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Crear VUELO
    @PostMapping
    public PostVueloResponse postVuelo(@RequestBody VueloRequest vueloRequest) {
        VueloDTO vuelo = vueloService.postVuelo(vueloRequest);
        PostVueloResponse response = PostVueloResponse.builder().vueloDTO(vuelo).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Actualizar plazas del VUELO
    @PostMapping("/{id}/reservar/{plazas}")
    public PostVueloResponse reservarPlazas(@PathVariable Long id, @PathVariable int plazas) {
        VueloDTO vueloActualizado = vueloService.reservarVuelo(id, plazas);
        PostVueloResponse response = new PostVueloResponse();
        response.setVueloDTO(vueloActualizado);
        response.setIsOk(vueloActualizado != null);

        if (vueloActualizado != null) {
            response.setMessage("Reserva realizada con éxito. Plazas actualizadas.");
        } else {
            response.setMessage("Error: No hay suficientes plazas disponibles o el vuelo no existe.");
        }

        response.setIsOk(true);
        return response;
    }

    //Endpoint Actualizar VUELO
    @PutMapping
    public PutVueloResponse putVuelo(@RequestBody VueloRequest vueloRequest) {
        VueloDTO vuelo = vueloService.putVuelo(vueloRequest);
        PutVueloResponse response = PutVueloResponse.builder().vueloDTO(vuelo).build();

        response.setIsOk(true);
        return response;
    }

    //Endpoint Eliminar Vuelo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVuelo(@PathVariable Long id) {
        vueloService.deleteVuelo(id);

        return ResponseEntity.ok("Vuelo eliminado correctamente");
    }
}
