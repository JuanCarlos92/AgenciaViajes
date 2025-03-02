package org.juancarlos.vuelos.model.response;
import lombok.*;
import org.juancarlos.vuelos.model.dto.VueloDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetVueloListResponse {
    List<VueloDTO> vuelosDTO;
}
