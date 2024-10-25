package com.eduardsol.espacial.entity;

import lombok.Getter;

@Getter
public enum TipoProduccion {
    SERIE(1),
    PELICULA(2);

    private final int valor;

    TipoProduccion(int valor){
        this.valor = valor;
    }
}
