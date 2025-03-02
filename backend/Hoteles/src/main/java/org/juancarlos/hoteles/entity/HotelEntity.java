package org.juancarlos.hoteles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hoteles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHotel", unique = true, nullable = false)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "categoria")
    private int categoria;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "disponible")
    private int disponible;
}