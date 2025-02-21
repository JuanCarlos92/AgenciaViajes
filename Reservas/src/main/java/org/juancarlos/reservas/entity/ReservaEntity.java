package org.juancarlos.reservas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva", unique = true, nullable = false)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dni")
    private String dni;
    @Column(name = "hotel", columnDefinition = "int UNSIGNED not null")
    private Long idHotel;
    @Column(name = "vuelo", columnDefinition = "int UNSIGNED not null")
    private Long idVuelo;

}

