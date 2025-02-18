package org.juancarlos.vuelos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vuelos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VueloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo", unique = true, nullable = false)
    private Long id;
    @Column(name = "company")
    private String company;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "plazas")
    private int plazas;
}
