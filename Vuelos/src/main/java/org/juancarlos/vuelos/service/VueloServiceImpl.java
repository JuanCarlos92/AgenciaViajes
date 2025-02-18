package org.juancarlos.vuelos.service;

import org.juancarlos.vuelos.converter.VueloConverter;
import org.juancarlos.vuelos.entity.VueloEntity;
import org.juancarlos.vuelos.model.dto.VueloDTO;
import org.juancarlos.vuelos.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public List<VueloDTO> getVuelosList(){
        List<VueloEntity> vueloEntity = vueloRepository.findAll();
        return VueloConverter.vueloEntityToDTO(vueloEntity);
    }

    @Override
    public VueloDTO getVueloId(Long id){
        VueloEntity vueloEntity = vueloRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Vuelo con id: " + id + " no encontrado"));
        return VueloConverter.vueloEntityToDTO(vueloEntity);
    }

    @Override
    public VueloDTO reservarVuelo(Long id, int plazas) {
        Optional<VueloEntity> optionalVuelo = vueloRepository.findById(id);

        if (optionalVuelo.isPresent()) {
            VueloEntity vuelo = optionalVuelo.get();

            if (vuelo.getPlazas() >= plazas) {
                vuelo.setPlazas(vuelo.getPlazas() - plazas);
                vueloRepository.save(vuelo);
                return VueloConverter.vueloEntityToDTO(vuelo);
            }
        }
        return null; // NO RESERVA
    }
}
