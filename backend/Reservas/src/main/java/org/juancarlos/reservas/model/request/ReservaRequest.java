package org.juancarlos.reservas.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaRequest {
    private Long idHotel;
    private Long idVuelo;
    private String nombre;
    private String dni;
    private int plazaHotel;
    private int plazaVuelo;
}
