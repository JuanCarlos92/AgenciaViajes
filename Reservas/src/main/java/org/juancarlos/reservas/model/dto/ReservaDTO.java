package org.juancarlos.reservas.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {
    private Long id;
    private String nombre;
    private String dni;
    private Long idHotel;
    private Long idVuelo;

}
