package com.eduardsol.espacial.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SeriePelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "serie_pelicula_id")
    private Long id;

    private String nombre;

    @Enumerated(EnumType.ORDINAL)
    private TipoProduccion tipoProduccion;

    private String genero;
}
