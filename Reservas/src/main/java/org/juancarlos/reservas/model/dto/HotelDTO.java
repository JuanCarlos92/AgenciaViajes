package org.juancarlos.reservas.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private Long idHotel;
    private String nombre;
    private int categoria;
    private Double precio;
    private int disponible;
}
