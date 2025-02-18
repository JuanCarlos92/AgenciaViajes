package org.juancarlos.vuelos.model.response;

import lombok.Getter;
import lombok.Setter;
import org.juancarlos.vuelos.model.dto.VueloDTO;

@Getter
@Setter
public class PutVueloResponse {
    private VueloDTO vueloDTO;
    private Boolean isOk;
    private String message;
}
