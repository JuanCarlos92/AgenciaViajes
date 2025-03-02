package org.juancarlos.reservas.model.response;

import lombok.*;
import org.juancarlos.reservas.model.dto.VueloDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetVueloResponse extends Response {
    private VueloDTO vueloDTO;
}
