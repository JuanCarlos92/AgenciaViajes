package org.juancarlos.vuelos.service;

import org.juancarlos.vuelos.model.dto.VueloDTO;
import org.juancarlos.vuelos.model.request.VueloRequest;

import java.util.List;

public interface VueloService {
    List<VueloDTO> getVuelosList();
    VueloDTO getVueloId(Long id);
    VueloDTO getVueloCompany(String company);
    VueloDTO postVuelo(VueloRequest vueloRequest);
    VueloDTO reservarVuelo(Long id,int plazas);
    VueloDTO putVuelo(VueloRequest vueloRequest);
    void deleteVuelo(Long id);
}
