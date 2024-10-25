package com.eduardsol.espacial.services;

import com.eduardsol.espacial.entity.NaveEspacial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NaveEspacialService {
    Optional<NaveEspacial> getById(Long id);
    Page<NaveEspacial> findAll(Pageable pageable);
    Page<NaveEspacial> getByName(String nombre, Pageable pageable);
    NaveEspacial save(NaveEspacial naveEspacial);
    Optional<NaveEspacial> delete(Long id);
    Optional<NaveEspacial> update(NaveEspacial naveEspcial);

}
