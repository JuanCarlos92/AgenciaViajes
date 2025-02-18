package org.juancarlos.hoteles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHotel", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "categoria", columnDefinition = "int UNSIGNED not null")
    private Long categoria;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "disponible", columnDefinition = "tinyint UNSIGNED not null")
    private Short disponible;

}