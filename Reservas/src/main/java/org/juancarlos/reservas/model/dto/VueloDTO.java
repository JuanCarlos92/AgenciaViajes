package org.juancarlos.reservas.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VueloDTO {
    private Long idVuelo;
    private String company;
    private String fecha;
    private double precio;
    private int plazas;
}
