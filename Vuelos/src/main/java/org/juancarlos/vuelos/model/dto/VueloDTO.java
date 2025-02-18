package org.juancarlos.vuelos.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VueloDTO {
    private Long id;
    private String company;
    private String fecha;
    private Double precio;
    private int plazas;
}
