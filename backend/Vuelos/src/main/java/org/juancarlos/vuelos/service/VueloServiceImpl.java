package org.juancarlos.vuelos.service;

import org.juancarlos.vuelos.converter.VueloConverter;
import org.juancarlos.vuelos.entity.VueloEntity;
import org.juancarlos.vuelos.model.dto.VueloDTO;
import org.juancarlos.vuelos.model.request.VueloRequest;
import org.juancarlos.vuelos.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    //Metodo Obtener Lista vuelos
    @Override
    public List<VueloDTO> getVuelosList() {
        List<VueloEntity> vueloEntity = vueloRepository.findAll();

        return VueloConverter.vueloEntityToDTO(vueloEntity);
    }

    //Metodo Obtener ID vuelo
    @Override
    public VueloDTO getVueloId(Long id) {
        VueloEntity vueloEntity = vueloRepository.findById(id).orElseThrow(() -> new RuntimeException("Vuelo con id: " + id + " no encontrado"));

        return VueloConverter.vueloEntityToDTO(vueloEntity);
    }

    //Metodo Obtener compañia vuelo
    @Override
    public VueloDTO getVueloCompany(String company) {
        VueloEntity vueloEntity = vueloRepository.findVueloByCompany(company);

        return VueloConverter.vueloEntityToDTO(vueloEntity);
    }

    //Metodo guardar vuelo
    @Override
    public VueloDTO postVuelo(VueloRequest vueloRequest) {
        VueloEntity vueloEntity = VueloConverter.vueloDTOtoEntity(vueloRequest);
        VueloEntity guardarVuelo = vueloRepository.save(vueloEntity);

        return VueloConverter.vueloEntityToDTO(guardarVuelo);
    }

    //Metodo guardar vuelo con una plaza menos
    @Override
    public VueloDTO reservarVuelo(Long id, int plazas) {
        Optional<VueloEntity> vueloEntity = vueloRepository.findById(id);

        //Si VueloEntity existe ... Lo obtiene y se almacena en vuelo (objeto de VueloEntity)
        if (vueloEntity.isPresent()) {
            VueloEntity vuelo = vueloEntity.get();
            /*
            Si las plazas son >= a las que hay en vuelo (VueloEntity)...
            se le resta y guarda el nuevo vuelo (VueloEntity)
            */
            if (vuelo.getPlazas() >= plazas) {
                vuelo.setPlazas(vuelo.getPlazas() - plazas);
                vueloRepository.save(vuelo);

                return VueloConverter.vueloEntityToDTO(vuelo);
            }
        }
        return null; // NO RESERVA EL VUELO
    }

    //Metodo actualizar vuelo
    @Override
    public VueloDTO putVuelo(VueloRequest vueloRequest) {
        VueloEntity vueloEntity = VueloConverter.vueloDTOtoEntity(vueloRequest);
        VueloEntity actualizarVuelo = vueloRepository.save(vueloEntity);

        return VueloConverter.vueloEntityToDTO(actualizarVuelo);
    }

    //metodo eliminar vuelo por ID
    @Override
    public void deleteVuelo(Long id) {
        VueloEntity vueloEntity = vueloRepository.findById(id).orElseThrow(() -> new RuntimeException("Vuelo con id: " + id + " no encontrado"));

        vueloRepository.delete(vueloEntity);
    }
}
