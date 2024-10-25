package com.eduardsol.espacial.repositories;

import com.eduardsol.espacial.entity.SeriePelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriePeliculaRepository extends JpaRepository<SeriePelicula, Long> {
    Page<SeriePelicula> findByNombre(String nombre, Pageable pageable);
}
