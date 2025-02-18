package org.juancarlos.vuelos.service;

import org.juancarlos.vuelos.model.dto.VueloDTO;

import java.util.List;

public interface VueloService {
    List<VueloDTO> getVuelosList();
    VueloDTO getVueloId(Long id);
    VueloDTO reservarVuelo(Long id,int plazas);

}
