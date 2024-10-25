package com.eduardsol.espacial.services.implementations;

import com.eduardsol.espacial.entity.NaveEspacial;
import com.eduardsol.espacial.repositories.NaveEspacialRepository;
import com.eduardsol.espacial.services.NaveEspacialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NaveEspecialServiceImpl implements NaveEspacialService {

    @Autowired
    private NaveEspacialRepository naveEspacialRepository;

    // Obtener una nave espacial por ID (caché la respuesta)
    @Override
    @Cacheable(value = "naves", key = "#id")
    public Optional<NaveEspacial> getById(Long id) {
        return naveEspacialRepository.findById(id);
    }

    // Listar todas las naves con paginación
    @Override
    public Page<NaveEspacial> findAll(Pageable pageable) {
        return naveEspacialRepository.findAll(pageable);
    }

    // Buscar naves por nombre con paginación
    @Override
    public Page<NaveEspacial> getByName(String nombre, Pageable pageable) {
        return naveEspacialRepository.findByNombreContaining(nombre, pageable);
    }

    // Guardar una nueva nave espacial (caché el nuevo valor guardado)
    @Override
    @CachePut(value = "naves", key = "#naveEspacial.id")
    public NaveEspacial save(NaveEspacial naveEspacial) {
        return naveEspacialRepository.save(naveEspacial);
    }

    // Eliminar una nave espacial por ID (evict del caché)
    @Override
    @CacheEvict(value = "naves", key = "#id")
    public Optional<NaveEspacial> delete(Long id) {
        Optional<NaveEspacial> naveEspacial = naveEspacialRepository.findById(id);

        if (naveEspacial.isPresent()) {
            naveEspacialRepository.deleteById(id);
        }

        return naveEspacial;
    }

    // Actualizar una nave espacial existente (actualizar en el caché)
    @Override
    @CachePut(value = "naves", key = "#naveEspcial.id")
    public Optional<NaveEspacial> update(NaveEspacial naveEspcial) {
        Optional<NaveEspacial> naveEspacial = naveEspacialRepository.findById(naveEspcial.getId());

        if (naveEspacial.isPresent()) {
            naveEspacialRepository.save(naveEspcial);
        }

        return naveEspacial;
    }
}
