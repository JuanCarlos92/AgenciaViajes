package org.juancarlos.vuelos.converter;

import org.juancarlos.vuelos.entity.VueloEntity;
import org.juancarlos.vuelos.model.dto.VueloDTO;

import java.util.ArrayList;
import java.util.List;
public class VueloConverter {
    //Convierte VueloEntity a VueloDTO
    public static VueloDTO vueloEntityToDTO(VueloEntity vueloEntity) {
    if(vueloEntity == null) return null;

    //Creamos un nuevo objeto--> VueloDTO
    return VueloDTO.builder()
            .id(vueloEntity.getId())
            .company(vueloEntity.getCompany())
            .fecha(vueloEntity.getFecha())
            .precio(vueloEntity.getPrecio())
            .plazas(vueloEntity.getPlazas())
            .build();
    }

    //Metodo para recorrer la lista
    public static List<VueloDTO> vueloEntityToDTO(List<VueloEntity> vueloEntities) {
        List<VueloDTO> vueloDTOs = new ArrayList<>();
        for(VueloEntity vueloEntity : vueloEntities){
            vueloDTOs.add(vueloEntityToDTO(vueloEntity));
        }
        return vueloDTOs;
    }
}
