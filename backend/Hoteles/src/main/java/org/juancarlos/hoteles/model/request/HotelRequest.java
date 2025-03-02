package org.juancarlos.hoteles.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {
    private Long id;
    private String nombre;
    private int categoria;
    private Double precio;
    private int disponible;
}
