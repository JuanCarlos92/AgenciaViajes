package org.juancarlos.vuelos.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VueloRequest {
    private Long id;
    private String company;
    private String fecha;
    private Double precio;
    private int plazas;
}
