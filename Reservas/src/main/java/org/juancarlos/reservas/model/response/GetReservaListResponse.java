package org.juancarlos.reservas.model.response;

import lombok.*;
import org.juancarlos.reservas.model.dto.ReservaDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetReservaListResponse {
    private List<ReservaDTO> reservasDTO;
}