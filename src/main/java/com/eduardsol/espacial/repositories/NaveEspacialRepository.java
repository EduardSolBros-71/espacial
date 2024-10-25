package com.eduardsol.espacial.repositories;

import com.eduardsol.espacial.entity.NaveEspacial;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface NaveEspacialRepository extends JpaRepository<NaveEspacial, Long> {
    Page<NaveEspacial> findByNombreContaining(String nombre, Pageable pageable);
}