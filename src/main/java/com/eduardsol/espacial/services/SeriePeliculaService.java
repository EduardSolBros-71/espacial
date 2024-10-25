package com.eduardsol.espacial.services;

import com.eduardsol.espacial.entity.SeriePelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SeriePeliculaService {
    Optional<SeriePelicula> getById(Long id);
    Page<SeriePelicula> findAll(Pageable pageable);
    Page<SeriePelicula> getByName(String nombre, Pageable pageable);
    SeriePelicula save(SeriePelicula naveEspacial);
    Optional<SeriePelicula> delete(Long id);
    Optional<SeriePelicula> update(SeriePelicula naveEspcial);
}
