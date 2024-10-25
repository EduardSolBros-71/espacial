package com.eduardsol.espacial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class NaveEspacial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "nave_id")
    private Long id;

    private String nombre;
    private int capacidad;

    @ManyToMany
    @JoinTable(
            name = "nave_serie_pelicula",
            joinColumns = @JoinColumn(name = "nave_id"),
            inverseJoinColumns = @JoinColumn(name = "serie_pelicula_id")
    )
    private List<SeriePelicula> seriePeliculas;

}