package org.juancarlos.hoteles.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private Long id;
    private String nombre;
    private int categoria;
    private Double precio;
    private int disponible;
}
