package org.juancarlos.vuelos.model.response;

import lombok.*;
import org.juancarlos.vuelos.model.dto.VueloDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutVueloResponse extends Response {
    private VueloDTO vueloDTO;
}
