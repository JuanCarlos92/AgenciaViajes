package org.juancarlos.reservas.model.response;

import lombok.*;
import org.juancarlos.reservas.model.dto.ReservaDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReservaResponse extends Response {
    private ReservaDTO reservaDTO;
}
